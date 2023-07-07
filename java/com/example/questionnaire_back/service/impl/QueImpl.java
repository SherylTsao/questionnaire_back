package com.example.questionnaire_back.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire_back.constants.RtnCode;
import com.example.questionnaire_back.entity.Answer;
import com.example.questionnaire_back.entity.Fillout;
import com.example.questionnaire_back.entity.Qoption;
import com.example.questionnaire_back.entity.Question;
import com.example.questionnaire_back.entity.Questionnaire;
import com.example.questionnaire_back.entity.Respondents;
import com.example.questionnaire_back.repository.AnswerDao;
import com.example.questionnaire_back.repository.FilloutDao;
import com.example.questionnaire_back.repository.QoptionDao;
import com.example.questionnaire_back.repository.QuestionDao;

import com.example.questionnaire_back.repository.QuestionnaireDao;
import com.example.questionnaire_back.repository.RespondentsDao;
import com.example.questionnaire_back.service.ifs.QueService;
import com.example.questionnaire_back.vo.QueRequest;
import com.example.questionnaire_back.vo.QueResponse;
import com.example.questionnaire_back.vo.QuestionAndQoptionsVo;

@Service
public class QueImpl implements QueService {

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private FilloutDao filloutDao;

	@Autowired
	private QoptionDao qoptionDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionnaireDao questionnaireDao;

	@Autowired
	private RespondentsDao respondentsDao;

	/*
	 * 填寫問卷者的基本資訊防呆
	 */
	private String patternPhone = "\\\\d{10}";
	private String patternEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	@Override
	public QueResponse addProject(QueRequest queRequest) {

		/*
		 * 防止輸入空白或錯誤 若'title'、'description'為空(null或"") 或'startAt'晚於'endAt':
		 * ->則回傳"入力が空です"
		 */
		if (!StringUtils.hasText(queRequest.getTitle()) || !StringUtils.hasText(queRequest.getDescription())) {
			return new QueResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		/*
		 * 若'startAt'、'endAt'沒有輸入: ->則帶入'startAt'預設為當日，'endAt'預設+7日
		 * 若'startAt'、'endAt'有輸入: ->則設為傳入的該日期
		 * 
		 */

		LocalDate startAt = (queRequest.getStartAt() == null) ? LocalDate.now() : queRequest.getStartAt();
		LocalDate endAt = (queRequest.getEndAt() == null) ? startAt.plusDays(7) : queRequest.getEndAt();
		/*
		 * 以下情況有兩種: 若'startAt'無輸入請求日期&'endAt'有輸入請求日期 若'startAt'&'endAt'皆有輸入請求日期:
		 * ->則回傳"正しくないリクエストです!"
		 */
		if (startAt.isAfter(endAt)) {
			return new QueResponse(RtnCode.INCORRECT.getMessage());
		}

//		利用建構方法將該表的req放入對應表值，並存入Dao資料庫
//		Questionaire questionaire = new Questionaire(queRequest.getTitle(), queRequest.getDescription(),
//				LocalDateTime.now(), queRequest.getStartAt(), queRequest.getEndAt());
//		questionaireDao.save(questionaire);
//		

		/*
		 * 先產生UUID並轉型成字串後，(p.s.轉型成字串型態是因為要值將放入資料庫,而資料庫MySQL的型態是VERCHAR!) 再呼叫新增方法
		 * "insertProject(questionnaire_id, title, description, created_at, start_at, end_at)"
		 * ，新增問卷資訊於資料庫
		 */
		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();
		questionnaireDao.insertProject(uuidString, queRequest.getTitle(), queRequest.getDescription(),
				LocalDateTime.now(), startAt, endAt);

		/*
		 * 將REQ的問題表的問題內容'text'、問題類型'type'、是否必填'required'&選項表的選項內容'content' 互相對應到
		 * 將上述提到的資料放在同一個class裡面(將資料封在同一個class內:封箱),再將箱子全部放入REQ那(包成List)
		 */

//		這個vo是為了將題目跟選項綁在一起,有Key/Value概念 ( 封箱 )
		List<QuestionAndQoptionsVo> questionAndQoptionsVoList = queRequest.getQuestionAndQoptionsVoList();
//		用來接Question這個entity的
		List<Question> questionList = new ArrayList<>();
//		用來接Qoption這個entity的
		List<Qoption> qoptionList = new ArrayList<>();

		/*
		 * 將包裝的List拿出來， 一個一個對應至問題表的'text'、'type'、'required'&選項表的'content'
		 */

//		開箱voList
		for (QuestionAndQoptionsVo item : questionAndQoptionsVoList) {

			/*
			 * 開箱問題表的'UUID'、問卷表的'UUID'、'text'、'type'、'required' 利用問題表的建構方法，放入箱子內對應的值
			 * 再add進去新的List內
			 * p.s.這裡問卷表的'UUID'不用轉型成字串型態,因為這是對question的entity操作,而entity的型態是UUID
			 */
			Question question = new Question(UUID.randomUUID(), uuid, item.getText(), item.getType(),
					item.isRequired());
			questionList.add(question);

			/*
			 * 開箱選項表的'content' 並將選項們用';'分割(再用陣列接住)
			 * 用此';'分割是因為前端REQ的同一題的選項會一同輸入，而選項跟選項之間會用';'分開
			 */

			String[] optionArr = item.getqOptions().split(";");

			/*
			 * 再將前述提到的陣列,陣列裡面放的是同一題 已分割好的 多個選項 利用FOR迴圈將各個選項取出來
			 * 同樣利用選項表的建構方法，放入選項表所對應的，問題表的'UUID'、'content'
			 */
//			迴圈當筆題目的選項
			for (String itemArr : optionArr) {
//				存取選項
				Qoption options = new Qoption(question.getQuestionId(), itemArr.trim());
				qoptionList.add(options);
			}

		}
		/*
		 * 放入問題表跟選項表的DAO ->回傳成功訊息
		 */
		questionDao.saveAll(questionList);
		qoptionDao.saveAll(qoptionList);
		return new QueResponse(RtnCode.SUCCESSFUL.getMessage());

	}

	@Override
	public QueResponse renewProject(QueRequest queRequest) {
		/*
		 * 1.將REQ問卷表的UUID從String型態變成UUID(因為資料庫的型態是VERCHAR) 再利用UUID從資料庫找到該筆問卷資訊
		 */
		UUID uuid = UUID.fromString(queRequest.getQuestionaireId());
		Optional<Questionnaire> queList = questionnaireDao.findById(uuid);

		/*
		 * 2.三元運算式判斷是否有修改REQ
		 */
		String title = (queRequest.getTitle().isEmpty()) ? queList.get().getTitle() : queRequest.getTitle();
		String Description = (queRequest.getDescription().isEmpty()) ? queList.get().getDescription()
				: queRequest.getDescription();
		LocalDate startAt = (queRequest.getStartAt() == null) ? queList.get().getStartAt() : queRequest.getStartAt();
		LocalDate endAt = (queRequest.getEndAt() == null) ? queList.get().getEndAt() : queRequest.getEndAt();
		/*
		 * 防呆: 若開始時間'startAt'早於'現在的日期' ->則回傳'見つかりませんでした'(因為已經在投票期間了,不可以再更動問卷資訊了)
		 * 否之則->3.呼叫方法,將問卷表的資訊填入
		 */

//		if (queRequest.getStartAt().isBefore(LocalDate.now())) {
//			return new QueResponse(RtnCode.NOT_FOUND.getMessage());
//		} else {
		questionnaireDao.updateInfo(queRequest.getQuestionaireId(), title, Description, queList.get().getCreatedAt(),
				startAt, endAt);
//			System.out.println(i);
//		}

		/*
		 * 4.呼叫查詢方法: 利用問卷表ID找到問題表的所有對應內容
		 */
		List<Question> questionnaire = questionDao.searchProjectsByQuestionnaireId(queRequest.getQuestionaireId());

		/*
		 * 5.利用FOR迴圈找出每一筆的問題表ID,並放在新的List(專門收集該筆問卷的所有問題表ID的List) 6.JPQL方法
		 * 刪除該筆問卷的所有問題(利用問題表的PK) 7.呼叫方法 刪除該筆問卷(利用問卷表的PK)
		 */

		List<UUID> questionIdList = new ArrayList<>();
		for (Question item : questionnaire) {
			questionIdList.add(item.getQuestionId());
		}
		qoptionDao.deleteByQuestionIdIn(questionIdList);
		questionDao.deleteProjectByQuestionnaireId(uuid);

		/*
		 * 8.重新新增一筆問卷跟所有問題的內容(跟addProject方法一樣)
		 */

//		這個vo是為了將題目跟選項綁在一起,有Key/Value概念 ( 封箱 )
		List<QuestionAndQoptionsVo> questionAndQoptionsVoList = queRequest.getQuestionAndQoptionsVoList();
//		用來接Question這個entity的
		List<Question> questionList = new ArrayList<>();
//		用來接Qoption這個entity的
		List<Qoption> qoptionList = new ArrayList<>();

		for (QuestionAndQoptionsVo item : questionAndQoptionsVoList) {

			/*
			 * 開箱問題表的'UUID'、問卷表的'UUID'、'text'、'type'、'required' 利用問題表的建構方法，放入箱子內對應的值
			 * 再add進去新的List內
			 * p.s.這裡問卷表的'UUID'不用轉型成字串型態,因為這是對question的entity操作,而entity的型態是UUID
			 */
			Question question = new Question(UUID.randomUUID(), uuid, item.getText(), item.getType(),
					item.isRequired());
			questionList.add(question);

			/*
			 * 開箱選項表的'content' 並將選項們用';'分割(再用陣列接住)
			 * 用此';'分割是因為前端REQ的同一題的選項會一同輸入，而選項跟選項之間會用';'分開
			 */

			String[] optionArr = item.getqOptions().split(";");

			/*
			 * 再將前述提到的陣列,陣列裡面放的是同一題 已分割好的 多個選項 利用FOR迴圈將各個選項取出來
			 * 同樣利用選項表的建構方法，放入選項表所對應的，問題表的'UUID'、'content'
			 */
//			迴圈當筆題目的選項
			for (String itemArr : optionArr) {
//				存取選項
				Qoption options = new Qoption(question.getQuestionId(), itemArr.trim());
				qoptionList.add(options);
			}

		}
		/*
		 * 放入問題表跟選項表的DAO ->回傳成功訊息
		 */
		questionDao.saveAll(questionList);
		qoptionDao.saveAll(qoptionList);
		return new QueResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public QueResponse deleteProject(QueRequest queRequest) {
		/*
		 * 刪除多筆問卷 1. 在req放List<String> questionnaire: -> 一次請求多筆questionnaireIds進來
		 * 2.再利用FOR迴圈將Ids一筆一筆轉成UUID後,放入另一個List<UUID> -> 收集好<UUID>IDs了 3.若此List不是空的請求: ->
		 * 呼叫JPQL方法
		 * 
		 */
//		為什麼REQ的List要放string型態的->因為前端判斷不出來UUID所以要用String
		/*
		 * List來接轉型成UUID的questionnaireIds List來接REQ的多筆questionnaireIds
		 */
		List<UUID> questionnaireUUIDs = new ArrayList<>();
		List<String> questionnaireIdsReq = queRequest.getQuestionnaire();

		for (String id : questionnaireIdsReq) {
			UUID uuid = UUID.fromString(id);
			questionnaireUUIDs.add(uuid);
		}
		/*
		 * 呼叫JPQL刪除多筆questionnaireIds的方法
		 */
		if (questionnaireUUIDs.isEmpty()) {
			return new QueResponse(RtnCode.NOT_FOUND.getMessage());
		}
		questionnaireDao.deleteByQuestionnaireIdIn(questionnaireUUIDs);

		/*
		 * 呼叫JPQL方法:利用問卷表Id找到該問題表的問題們,集中刪除
		 */
		List<UUID> questionUUIDs = new ArrayList<>();
		List<Question> questionList = questionDao.findByQuestionnaireIdIn(questionnaireUUIDs);
		for (Question item : questionList) {
			questionUUIDs.add(item.getQuestionId());
		}
		// 刪除問題:利用問卷ID
		questionDao.deleteByQuestionnaireIdIn(questionnaireUUIDs);
		// 刪除選項:利用問題ID
		qoptionDao.deleteByQuestionIdIn(questionUUIDs);
		return new QueResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public QueResponse findProjects(QueRequest queRequest) {

		/*
		 * 查詢問卷資訊 三元運算子: 1.若問卷標題'title'為空->回傳"" 若問卷標題'title'不為空->回傳REQ的問卷標題
		 * 2.若開始時間'startAt'為空->回傳自己設定的日期 若開始時間'startAt不為空->回傳REQ的開始時間
		 * 3.若結束時間'endAt'為空->回傳現在的日期 若結束時間'endAt'不為空->回傳REQ的結束時間
		 * 
		 */

		String title = !StringUtils.hasText(queRequest.getTitle()) ? "" : queRequest.getTitle();
		LocalDate startAt = (queRequest.getStartAt() == null) ? LocalDate.of(1911, 01, 01) : queRequest.getStartAt();
		LocalDate endAt = (queRequest.getEndAt() == null) ? LocalDate.of(9999, 01, 01) : queRequest.getEndAt();

		/*
		 * 呼叫查詢方法
		 */
		List<Questionnaire> list = questionnaireDao.searchProjectsByCreatedAtDesc(title, startAt, endAt);
		return list.size() == 0 ? new QueResponse(RtnCode.NOT_FOUND.getMessage())
				: new QueResponse(list, RtnCode.SUCCESS.getMessage());

	}

	@Override
	public QueResponse filloutReport(QueRequest queRequest) {

		/*
		 * 1.填寫者的基本資訊防呆:姓名、Email(須符合格式)、年齡(數字)、性別、地址、電話號碼(數字10碼) 2.存入(addInfo)方法
		 */
		if (!StringUtils.hasText(queRequest.getName()) || !StringUtils.hasText(queRequest.getEmail())
				|| !StringUtils.hasText(queRequest.getGender()) || !StringUtils.hasText(queRequest.getAddress())) {
			return new QueResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
//		if (!queRequest.getPhone().matches(patternPhone)) {
//			return new QueResponse(RtnCode.INCORRECT.getMessage());
//		}
//		if (!queRequest.getEmail().matches(patternEmail)) {
//			return new QueResponse(RtnCode.INCORRECT.getMessage());
//		}
		if (queRequest.getAge() >= 200 || queRequest.getAge() <= 0) {
			return new QueResponse(RtnCode.INCORRECT.getMessage());
		}
//		respondentsDao.addInfo(queRequest.getName(), queRequest.getEmail(), queRequest.getAge(), queRequest.getGender(),
//				queRequest.getAddress(), queRequest.getPhone());
		Respondents respondents = new Respondents(queRequest.getName(), queRequest.getEmail(), queRequest.getAge(),
				queRequest.getGender(), queRequest.getAddress(), queRequest.getPhone());
		Respondents respondentsInfo = respondentsDao.save(respondents);
		// --
		UUID questionaireId = UUID.fromString(queRequest.getQuestionaireId());
		Fillout fillout = new Fillout(respondentsInfo.getParticipantId(), LocalDateTime.now(), questionaireId,
				queRequest.getName());
		filloutDao.save(fillout);
		List<Integer> optionsIdReq = queRequest.getOptionList();
		List<Qoption> optionsList = qoptionDao.findAllById(optionsIdReq);
		List<Answer> answerList = new ArrayList<>();
		for (var item : optionsList) {
			Answer answer = new Answer(questionaireId, item.getQuestionId(), respondentsInfo.getParticipantId(),
					item.getOptionId(), item.getContent());
			answerList.add(answer);
		}
		answerDao.saveAll(answerList);
		/*
		 * 找到對應要填寫的問卷表&問題&選項
		 */

		// 使用轉換後的 UUID 進行後續操作
//		UUID uuid = UUID.fromString(queRequest.getQuestionaireId());
//		Optional<Questionnaire> questionnaire = questionnaireDao.findById(uuid);
////		找到該問卷表後要秀出問卷表title、description、start_at、end_at
//		
//		/*
//		 * 呼叫方法:找到該問卷的所有問題表資訊
//		 */
//		List<Question> question = questionDao.searchProjectsByQuestionnaireId(queRequest.getQuestionaireId());
//		
//		/*
//		 * 利用問卷表PK找到要填寫的問卷表title、description、start_at、end_at
//		 * &找到問題表PK(text、type、required)&找到選項表(content)
//		 */
//		List<UUID> questionnaireUUIDs = new ArrayList<>();
//		List<String> questionnaireIdsReq = queRequest.getQuestionnaire();

		return new QueResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public QueResponse showWriteTime(QueRequest queRequest) {
		UUID questionnaireId = UUID.fromString(queRequest.getQuestionaireId());
//		可改SQL語法
//		利用填寫時間表(DAO)問卷IDs找到多筆回饋的填寫時間資料
		List<Fillout> filloutList = filloutDao.findByQuestionnaireId(questionnaireId);
		QueResponse res = new QueResponse();
		res.setMessage(RtnCode.SUCCESSFUL.getMessage());
//		set時間表資料
		res.setFileList(filloutList);
		return res;
	}

	@Override
	public QueResponse showReport(QueRequest queRequest) {
		List<Answer> ansList = new ArrayList<>();
		Respondents respondents = new Respondents();
		UUID uuid = UUID.fromString(queRequest.getQuestionaireId());
		Questionnaire questionnaire = questionnaireDao.findById(uuid).get();
		if (queRequest.getParticipantId() != 0) {
			respondents = respondentsDao.findById(queRequest.getParticipantId()).get();
			ansList = answerDao.findByParticipantId(queRequest.getParticipantId());
		}
//		翻她回答的list
		List<Question> questionList = questionDao.searchProjectsByQuestionnaireId(queRequest.getQuestionaireId());
		List<UUID> uuidList = new ArrayList<>();
		for (var item : questionList) {
			uuidList.add(item.getQuestionId());
		}
		List<Qoption> qoptionList = qoptionDao.findByQuestionIdIn(uuidList);
		return new QueResponse(questionnaire,respondents, ansList, qoptionList, questionList, RtnCode.SUCCESSFUL.getMessage());
	}

//統計
	@Override
	public QueResponse showAllReport(QueRequest queRequest) {
		Map<Integer, Integer> map = new HashMap<>();
		UUID uuid = UUID.fromString(queRequest.getQuestionaireId());
//		找出問卷底下的題目
		List<Question> questionList = questionDao.searchProjectsByQuestionnaireId(queRequest.getQuestionaireId());
		List<UUID> uuidList = new ArrayList<>();
//		把問題的id蒐集起來,用於翻選項
		for (var item : questionList) {
			uuidList.add(item.getQuestionId());
		}
		List<Integer> intList = new ArrayList<>();
//		翻選項
		List<Qoption> qoptionList = qoptionDao.findByQuestionIdIn(uuidList);
//		選項的流水號蒐集起來，用於計算&&翻回答
		for (var item : qoptionList) {
			intList.add(item.getOptionId());
		}
//		翻回答 藉由選項的id
		List<Answer> ansList = answerDao.findByOptionIdIn(intList);
//		迴圈選項
		for (var opItem : qoptionList) {
			int x = 0;
//			選項的id作為key/統計次數作為value
			map.put(opItem.getOptionId(), x);
//			迴圈答案
			for (var ansItem : ansList) {
//				如果一樣，代表有一個人選
				if (ansItem.getOptionId() == opItem.getOptionId()) {
					x++;
//					選項的id作為key/統計次數作為value
					map.put(ansItem.getOptionId(), x);
				}

			}
		}
		return new QueResponse(ansList, qoptionList, questionList, map);
	}

	@Override
	public QueResponse showQuestionnaireInfo(QueRequest queRequest) {
		UUID uuid = UUID.fromString(queRequest.getQuestionaireId());
//		翻到問卷資料
		Questionnaire questionnaire = questionnaireDao.findById(uuid).get();
//		翻問卷底下的問題
		List<Question> questionList = questionDao.searchProjectsByQuestionnaireId(queRequest.getQuestionaireId());
		List<UUID> uuidList = new ArrayList<>();
//	    將所有問題的id蒐集起來 用於翻選項
		for (Question item : questionList) {
			uuidList.add(item.getQuestionId());
		}
//		才能找到問題底下的選項
		List<Qoption> qoptionList = qoptionDao.findByQuestionIdIn(uuidList);
//		封裝的箱子有的屬性:題目/選項/單多選/必填
		List<QuestionAndQoptionsVo> questionAndQoptionsVoList = new ArrayList<>();
//		跑問題的迴圈
		for (var item : questionList) {
//			
			String opString = "";
//			跑選項的迴圈
			for (var itemOp : qoptionList) {
//				比較問題的id 如果一樣 代表找到問題底下的選項
				if (item.getQuestionId().equals(itemOp.getQuestionId())) {
//					將所有選項串接起來
					opString += itemOp.getContent() + ";";
				}
			}
//			漢堡;薯條;substring 切掉後 >漢堡;薯條
//			
			String opStringSub = opString.substring(0, opString.length() - 1);
			QuestionAndQoptionsVo questionAndQoptionsVo = new QuestionAndQoptionsVo(item.getText(), item.getType(),
					item.isRequired(), opStringSub);
			questionAndQoptionsVoList.add(questionAndQoptionsVo);
		}
		return new QueResponse(questionnaire, questionAndQoptionsVoList);
	}

}

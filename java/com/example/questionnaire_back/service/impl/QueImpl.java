package com.example.questionnaire_back.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire_back.constants.RtnCode;
import com.example.questionnaire_back.entity.Qoption;
import com.example.questionnaire_back.entity.Question;
import com.example.questionnaire_back.entity.Questionnaire;
import com.example.questionnaire_back.repository.AnswerDao;
import com.example.questionnaire_back.repository.FilloutDao;
import com.example.questionnaire_back.repository.QoptionDao;
import com.example.questionnaire_back.repository.QuestionDao;
import com.example.questionnaire_back.repository.QuestionaireDao;
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
	private QuestionaireDao questionaireDao;

	@Autowired
	private RespondentsDao respondentsDao;

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
		 * 呼叫新增方法
		 * "insertProject(questionnaire_id, title, description, created_at, start_at, end_at)"
		 * ，新增問卷資訊於資料庫
		 */
		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();
		questionaireDao.insertProject(uuidString, queRequest.getTitle(), queRequest.getDescription(),
				LocalDateTime.now(), startAt, endAt);

//		這個vo是為了將題目跟選項綁在一起,有Key/Value概念 ( 封箱 )
		List<QuestionAndQoptionsVo> questionAndQoptionsVoList = queRequest.getQuestionAndQoptionsVoList();
//		用來接Question這個entity的
		List<Question> questionList = new ArrayList<>();
//		用來接Qoption這個entity的
		List<Qoption> qoptionList = new ArrayList<>();
//		開箱voList
		for (QuestionAndQoptionsVo item : questionAndQoptionsVoList) {
//			開箱題目
			Question question = new Question(UUID.randomUUID(), uuid, item.getText(), item.getType(),
					item.isRequired());
			System.out.println(question.getQuestionnaireId());
			questionList.add(question);
//			切割選項
			String[] optionArr = item.getqOptions().split(";");
//			迴圈當筆題目的選項
			for (String itemArr : optionArr) {
//				存取選項
				Qoption options = new Qoption(question.getQuestionId(), itemArr.trim());
				qoptionList.add(options);
			}

		}
		questionDao.saveAll(questionList);
		qoptionDao.saveAll(qoptionList);
		return new QueResponse("suss");

	}

	@Override
	public QueResponse renewProject(QueRequest queRequest) {
		UUID uuid = UUID.fromString(queRequest.getQuestionId());
		questionaireDao.findById(uuid);
		return null;
	}

	@Override
	public QueResponse deleteProject(QueRequest queRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueResponse findProjects(QueRequest queRequest) {

		String title = !StringUtils.hasText(queRequest.getTitle()) ? "" : queRequest.getTitle();
		LocalDate startAt = (queRequest.getStartAt() == null) ? LocalDate.of(1911, 01, 01) : queRequest.getStartAt();
		LocalDate endAt = (queRequest.getEndAt() == null) ? LocalDate.now() : queRequest.getEndAt();
		List<Questionnaire> list = questionaireDao.searchProjectsByCreatedAtDesc(title, startAt, endAt);
		return list.size() == 0 ? new QueResponse("查無資料") : new QueResponse(list, "suss");

		/*
		 * 查找資料 雙三元式包覆，邏輯判斷如下 若'project'內容為空(null或""): 則回傳null 若'cc'為null: 呼叫搜尋方法
		 * "searchProjectsByCcUp(project)"，查詢方案名稱為'project'的所有資料 若以上皆非: 呼叫搜尋方法
		 * "searchProjectsByThresholdUp(project, cc)"，查詢方案名稱為'project'及排氣量為'cc'的所有資料
		 */

	}

	@Override
	public QueResponse filloutReport(QueRequest queRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueResponse showReport(QueRequest queRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueResponse showAllReport(QueRequest queRequest) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override

}

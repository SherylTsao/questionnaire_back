package com.example.questionnaire_back;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questionnaire_back.entity.Answer;
import com.example.questionnaire_back.entity.Qoption;
import com.example.questionnaire_back.entity.Question;
import com.example.questionnaire_back.entity.Respondents;
import com.example.questionnaire_back.service.ifs.QueService;
import com.example.questionnaire_back.vo.QueRequest;
import com.example.questionnaire_back.vo.QueResponse;
import com.example.questionnaire_back.vo.QuestionAndQoptionsVo;

@SpringBootTest
class QuestionnaireBackApplicationTests {
	@Autowired
	QueService queService;

	@Test
	void add() {
		QueRequest req = new QueRequest();
		req.setTitle("第七份標題");
		req.setDescription("第一份描述");
		req.setStartAt(LocalDate.of(2023, 3, 02));
		req.setEndAt(LocalDate.of(2023, 3, 07));
		QuestionAndQoptionsVo vo1 = new QuestionAndQoptionsVo("肚子餓要吃什麼", "單", true, "漢堡;三明治");
		QuestionAndQoptionsVo vo2 = new QuestionAndQoptionsVo("口渴要喝什麼", "多", false, "水");
		List<QuestionAndQoptionsVo> voList = new ArrayList<>();
		voList.add(vo1);
		voList.add(vo2);
		req.setQuestionAndQoptionsVoList(voList);
		QueResponse res = queService.addProject(req);
		System.out.println(res.getMessage());

	}

	@Test
	void search() {
		QueRequest req = new QueRequest();
//		req.setTitle("標");
//		req.setStartAt(LocalDate.of(2023, 3, 01));
//		req.setEndAt(LocalDate.of(2023, 3, 9));
		QueResponse res = queService.findProjects(req);
		if (res.getMessage().equals("suss")) {
			System.out.println(res.getQueList().size());
			for (var item : res.getQueList()) {
				System.out.println(item.getTitle());
			}
		} else {
			System.out.println(res.getMessage());

		}

	}

	@Test
	void update() {
		QueRequest req = new QueRequest();
		req.setQuestionaireId("527f5625-efd2-4cb9-9793-550ad27f01cf");
		req.setTitle("第一份標題 U");
		req.setDescription("第二份描述");
		req.setStartAt(LocalDate.of(2023, 7, 02));
		req.setEndAt(LocalDate.of(2023, 7, 07));
		QuestionAndQoptionsVo vo1 = new QuestionAndQoptionsVo("肚子餓要吃什麼?", "單", true, "漢堡;三明治;薯條");
		QuestionAndQoptionsVo vo2 = new QuestionAndQoptionsVo("口渴要喝什麼~~?", "單", false, "水");
		List<QuestionAndQoptionsVo> voList = new ArrayList<>();
		voList.add(vo1);
		voList.add(vo2);
		req.setQuestionAndQoptionsVoList(voList);
		QueResponse res = queService.renewProject(req);
		System.out.println(res.getMessage());

	}

	@Test
	void delete() {
		QueRequest req = new QueRequest();
		List<String> listList = new ArrayList<>();
		listList.add("7e649cc5-a3a8-4c76-94d2-391dc7a6e4ab");
		listList.add("9eae0515-3a3d-45ef-b8e4-b8e0fa2ce25a");
		req.setQuestionnaire(listList);
		QueResponse res = queService.deleteProject(req);
		System.out.println(res.getMessage());
	}

	@Test
	void filloutReport() {
		QueRequest req = new QueRequest();
		req.setName("d");
		req.setAddress("高雄市");
		req.setAge(23);
		req.setEmail("a0973038822@gmail.com");
		req.setPhone("0973038822");
		req.setGender("男");
		req.setQuestionaireId("527f5625-efd2-4cb9-9793-550ad27f01cf");
		List<Integer> opList = new ArrayList<>();
		opList.add(90);
		opList.add(91);
		opList.add(92);
		req.setOptionList(opList);
		QueResponse res = queService.filloutReport(req);
		System.out.println(res.getMessage());

	}

	@Test
	void showReport() {
		QueRequest req = new QueRequest();
		req.setParticipantId(5);
		req.setQuestionaireId("527f5625-efd2-4cb9-9793-550ad27f01cf");
		QueResponse res = queService.showReport(req);
//		個人資訊
		Respondents resp = res.getRespondents();
		System.out.println(resp.getName() + " 名子");
		List<Integer> intList = new ArrayList<>();
//		蒐集起來 用於contains 有包含到代表有選
		for (var itemAns : res.getAnswerList()) {
			intList.add(itemAns.getOptionId());
		}
//		迴圈題目
		for (var item : res.getQuestionList()) {
			System.out.println("-------------------------");
			System.out.println(item.getText() + " 題目 ");
//			迴圈選項
			for (var opItem : res.getQoptionList()) {
//				如果題目id一樣 代表是問題底下的選項
				if (item.getQuestionId().equals(opItem.getQuestionId())) {
					System.out.print(opItem.getContent() + " 選項 ");
//					如果有包含到，代表有選 那就打勾
					if (intList.contains(opItem.getOptionId())) {
						System.out.print(" 打勾 ");
					}
					System.out.println("");
				}
			}

		}
		System.out.println("");
		System.out.println(res.getMessage());

	}

	@Test
	void vo() {
		QueRequest req = new QueRequest();
		req.setQuestionaireId("527f5625-efd2-4cb9-9793-550ad27f01cf");
		QueResponse res = queService.showQuestionnaireInfo(req);
		List<QuestionAndQoptionsVo> voList = res.getQuestionAndQoptionsVoList();
		System.out.println(res.getQuestionnaire().getTitle() + "問卷名稱");
		for (var item : voList) {
			System.out.println(item.getText() + " 題目");
			System.out.println(item.getqOptions() + " 選項");
			System.out.println(item.getType() + " 類型");
			System.out.println(item.isRequired() + " 必填與否");
		}

	}

	@Test
	void statistics() {
		QueRequest req = new QueRequest();
		req.setQuestionaireId("527f5625-efd2-4cb9-9793-550ad27f01cf");
		QueResponse res = queService.showAllReport(req);
//		迴圈題目
		for (var item : res.getQuestionList()) {
			System.out.println(item.getText() + " 題目");
//			迴圈選項
			for (var opItem : res.getQoptionList()) {
//				如果是問題底下的選項
				if (item.getQuestionId().equals(opItem.getQuestionId())) {
//					迴圈map <選項id,次數>
					for (var ansItem : res.getStatisticsMap().entrySet()) {
//						如果選項的id == map的key值
						if (opItem.getOptionId() == ansItem.getKey()) {
							System.out.print(opItem.getContent() + " 選項 ");
							System.out.print(ansItem.getValue() + " 個人選 ");
							System.out.println("");
						}
					}
				}
			}
		}

	}

}

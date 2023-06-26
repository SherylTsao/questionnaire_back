package com.example.questionnaire_back;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		req.setTitle("第一份標題");
		req.setDescription("第一份描述");
		req.setStartAt(LocalDate.of(2023, 3, 01));
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

}

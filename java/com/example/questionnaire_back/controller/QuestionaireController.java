package com.example.questionnaire_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire_back.service.ifs.QueService;
import com.example.questionnaire_back.vo.QueRequest;
import com.example.questionnaire_back.vo.QueResponse;

@CrossOrigin
@RestController
public class QuestionaireController {

	@Autowired // 指定託管類別
	private QueService queService;

	@PostMapping(value = "add_project") // 連結外部
	public QueResponse addProject(@RequestBody QueRequest request) {
		return queService.addProject(request);
		// 連結內部
	}

	@PostMapping(value = "renew_project") // 連結外部
	public QueResponse renewProject(@RequestBody QueRequest request) {
		return queService.renewProject(request);
		// 連結內部
	}

	

	@PostMapping(value = "delete_project") // 連結外部
	public QueResponse deleteProject(@RequestBody QueRequest request) {
		return queService.deleteProject(request);
		// 連結內部
	}

	@PostMapping(value = "find_projects") // 連結外部
	public QueResponse findProjects(@RequestBody QueRequest request) {
		return queService.findProjects(request);
		// 連結內部
	}

	@PostMapping(value = "fillout_report") // 連結外部
	public QueResponse filloutReport(@RequestBody QueRequest request) {
		return queService.filloutReport(request);
		// 連結內部
	}
	
	@PostMapping(value = "show_write_time") // 連結外部
	public QueResponse showWriteTime(@RequestBody QueRequest request) {
		return queService.showWriteTime(request);
		// 連結內部
	}

	@PostMapping(value = "show_report") // 連結外部
	public QueResponse showReport(@RequestBody QueRequest request) {
		return queService.showReport(request);
		// 連結內部
	}
	@PostMapping(value = "show_all_report") // 連結外部
	public QueResponse showAllReport(@RequestBody QueRequest request) {
		return queService.showAllReport(request);
		// 連結內部
	}
	@PostMapping(value = "showQuestionnaireInfo") // 連結外部
	public QueResponse showQuestionnaireInfo(@RequestBody QueRequest request) {
		return queService.showQuestionnaireInfo(request);
		// 連結內部
	}
}

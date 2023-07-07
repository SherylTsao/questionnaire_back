package com.example.questionnaire_back.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.example.questionnaire_back.entity.Answer;
import com.example.questionnaire_back.entity.Fillout;
import com.example.questionnaire_back.entity.Qoption;
import com.example.questionnaire_back.entity.Question;
import com.example.questionnaire_back.entity.Questionnaire;
import com.example.questionnaire_back.entity.Respondents;



public class QueResponse {

	// 屬性
	
//	private List<Answer> answerList;
//	private List<Fillout> filloutList;
//	private List<Qoption> qoptionList;
//	private List<Question> questionList;
	private List<Questionnaire> queList;
//	private List<Respondents> respondentsList;
	private String message;
	/*
	 * 建構方法
	 * 1.()
	 * 2.(message)
	 * 3.(answerList, message)
	 * 4.(filloutList, message)
	 */
	
	public QueResponse() {
		super();	
	}

	public QueResponse(String message) {
		super();
		this.message = message;
	}

	
	public QueResponse(List<Questionnaire> queList, String message) {
		super();
		this.queList = queList;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Questionnaire> getQueList() {
		return queList;
	}

	public void setQueList(List<Questionnaire> queList) {
		this.queList = queList;
	}


	
	
}

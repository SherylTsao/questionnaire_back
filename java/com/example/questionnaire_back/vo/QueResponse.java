package com.example.questionnaire_back.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.questionnaire_back.entity.Answer;
import com.example.questionnaire_back.entity.Fillout;
import com.example.questionnaire_back.entity.Qoption;
import com.example.questionnaire_back.entity.Question;
import com.example.questionnaire_back.entity.Questionnaire;
import com.example.questionnaire_back.entity.Respondents;

public class QueResponse {

	// 屬性
	private Respondents respondents;
	private List<Answer> answerList;
	private List<Fillout> filloutList;
	private List<Qoption> qoptionList;
	private List<Question> questionList;
	private List<Questionnaire> queList;
	private List<Fillout> fileList;
//	private List<Respondents> respondentsList;
	private String message;
	private Questionnaire questionnaire;
	private List<QuestionAndQoptionsVo> questionAndQoptionsVoList;
	private Map<Integer, Integer> statisticsMap;
	/*
	 * 建構方法 1.() 2.(message) 3.(answerList, message) 4.(filloutList, message)
	 */

	public QueResponse() {
		super();
	}

	public QueResponse(List<Answer> answerList, List<Qoption> qoptionList, List<Question> questionList,
			Map<Integer, Integer> statisticsMap) {
		super();
		this.answerList = answerList;
		this.qoptionList = qoptionList;
		this.questionList = questionList;
		this.statisticsMap = statisticsMap;
	}

	public QueResponse(Map<Integer, Integer> statisticsMap) {
		super();
		this.statisticsMap = statisticsMap;
	}

	public QueResponse(Questionnaire questionnaire, List<QuestionAndQoptionsVo> questionAndQoptionsVoList) {
		super();
		this.questionnaire = questionnaire;
		this.questionAndQoptionsVoList = questionAndQoptionsVoList;
	}

	public QueResponse(List<QuestionAndQoptionsVo> questionAndQoptionsVoList) {
		super();
		this.questionAndQoptionsVoList = questionAndQoptionsVoList;
	}

	public QueResponse(Questionnaire questionnaire,Respondents respondents, List<Answer> answerList, List<Qoption> qoptionList,
			List<Question> questionList, String message) {
		super();
		this.questionnaire=questionnaire;
		this.respondents = respondents;
		this.answerList = answerList;
		this.qoptionList = qoptionList;
		this.questionList = questionList;
		this.message = message;
	}

	public Respondents getRespondents() {
		return respondents;
	}

	public void setRespondents(Respondents respondents) {
		this.respondents = respondents;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public List<Fillout> getFilloutList() {
		return filloutList;
	}

	public void setFilloutList(List<Fillout> filloutList) {
		this.filloutList = filloutList;
	}

	public List<Qoption> getQoptionList() {
		return qoptionList;
	}

	public void setQoptionList(List<Qoption> qoptionList) {
		this.qoptionList = qoptionList;
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

	public List<Fillout> getFileList() {
		return fileList;
	}

	public void setFileList(List<Fillout> fileList) {
		this.fileList = fileList;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public List<QuestionAndQoptionsVo> getQuestionAndQoptionsVoList() {
		return questionAndQoptionsVoList;
	}

	public void setQuestionAndQoptionsVoList(List<QuestionAndQoptionsVo> questionAndQoptionsVoList) {
		this.questionAndQoptionsVoList = questionAndQoptionsVoList;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Map<Integer, Integer> getStatisticsMap() {
		return statisticsMap;
	}

	public void setStatisticsMap(Map<Integer, Integer> statisticsMap) {
		this.statisticsMap = statisticsMap;
	}

}

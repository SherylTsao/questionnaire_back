package com.example.questionnaire_back.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.questionnaire_back.entity.Answer;

public class QueRequest {

	// 屬性
//  -----
	private String questionaireId;
	private String questionId;
//  -----	
	private int optionId;
	private String answerText;
	private LocalDateTime filling_time;
	private String content;
	private String text;
	private String type;
	private boolean required;
	private String title;
	private String description;
	private LocalDateTime createdAt;
	private LocalDate startAt;
	private LocalDate endAt;
	private String name;
	private String email;
	private int age;
	private String gender;
	private String address;
	private String phone;
	private int participantId;
//		---
	private List<QuestionAndQoptionsVo> questionAndQoptionsVoList;
	private List<String> questionnaire;
	private List<String> question;
	private List<Integer> optionList;
	private List<Answer> answers;

	// getters & setters

	public String getQuestionId() {
		return questionId;
	}

	public String getQuestionaireId() {
		return questionaireId;
	}

	public void setQuestionaireId(String questionaireId) {
		this.questionaireId = questionaireId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public LocalDateTime getFilling_time() {
		return filling_time;
	}

	public void setFilling_time(LocalDateTime filling_time) {
		this.filling_time = filling_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDate startAt) {
		this.startAt = startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDate endAt) {
		this.endAt = endAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<QuestionAndQoptionsVo> getQuestionAndQoptionsVoList() {
		return questionAndQoptionsVoList;
	}

	public void setQuestionAndQoptionsVoList(List<QuestionAndQoptionsVo> questionAndQoptionsVoList) {
		this.questionAndQoptionsVoList = questionAndQoptionsVoList;
	}

	public List<String> getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(List<String> questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<String> getQuestion() {
		return question;
	}

	public void setQuestion(List<String> question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Integer> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Integer> optionList) {
		this.optionList = optionList;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

}

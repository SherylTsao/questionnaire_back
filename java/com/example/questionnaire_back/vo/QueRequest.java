package com.example.questionnaire_back.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class QueRequest {

	// 屬性
//	private int questionId;
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
//		---
	private String questionId;
	private List<QuestionAndQoptionsVo> questionAndQoptionsVoList;

	// getters & setters
	public String getQuestionId() {
		return questionId;
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

}

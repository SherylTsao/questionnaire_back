package com.example.questionnaire_back.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "q_option")
public class Qoption {
	@Id
	@Column(name = "option_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 流水編號遞增
	private int optionId;
	
	@Column(name = "question_id")
	@Type(type = "uuid-char")
	private UUID questionId;
	
	@Column(name = "content")
	private String content;

	public Qoption() {
		super();
	}

	

	

	public Qoption(UUID questionId, String content) {
		super();
		this.questionId = questionId;
		this.content = content;
	}





	public UUID getQuestionId() {
		return questionId;
	}





	public void setQuestionId(UUID questionId) {
		this.questionId = questionId;
	}





	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



}

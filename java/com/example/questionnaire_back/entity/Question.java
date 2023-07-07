package com.example.questionnaire_back.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@Column(name = "question_id")
	@Type(type = "uuid-char")
	private UUID questionId;

	@Column(name = "questionnaire_id")
	@Type(type = "uuid-char")
	private UUID questionnaireId;

	@Column(name = "text")
	private String text;

	@Column(name = "type")
	private String type;

	@Column(name = "required")
	private boolean required;

	public Question() {
		super();
	}

	public Question(UUID questionId, UUID questionnaireId, String text, String type, boolean required) {
		super();
		this.questionId = questionId;
		this.questionnaireId = questionnaireId;
		this.text = text;
		this.type = type;
		this.required = required;
	}

	public Question(UUID questionnaireId, String text, String type, boolean required) {

		this.questionnaireId = questionnaireId;
		this.text = text;
		this.type = type;
		this.required = required;
	}

	public UUID getQuestionId() {
		return questionId;
	}

	public void setQuestionId(UUID questionId) {
		this.questionId = questionId;
	}

	public UUID getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(UUID questionnaireId) {
		this.questionnaireId = questionnaireId;
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

}

package com.example.questionnaire_back.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {

	@Id
	@Column(name = "questionnaire_id")
	@Type(type = "uuid-char")
	private UUID questionnaireId ;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "start_at")
	private LocalDate startAt;

	@Column(name = "end_at")
	private LocalDate endAt;

	public Questionnaire() {
		super();
	}

	public Questionnaire(String title, String description, LocalDateTime createdAt, LocalDate startAt,
			LocalDate endAt) {
		super();
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.startAt = startAt;
		this.endAt = endAt;
	}

	public UUID getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(UUID questionnaireId) {
		this.questionnaireId = questionnaireId;
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

}

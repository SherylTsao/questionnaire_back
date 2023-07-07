package com.example.questionnaire_back.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "fillout")
public class Fillout {
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 流水編號遞增
	@Id
	@Column(name = "fillout_id")
	private int filloutId;// 打建構方法可省略,因為系統自動遞增,所以自己輸入的無效

	@Column(name = "participant_id")
	private int participantId;

	@Column(name = "filling_time")
	private LocalDateTime filling_time;

	@Type(type = "uuid-char")
	@Column(name = "questionnaire_id")
	private UUID questionnaireId;

	@Column(name = "name")
	private String name;

	public Fillout() {

	}

	public Fillout(int participantId, LocalDateTime filling_time, UUID questionnaireId, String name) {
		super();
		this.participantId = participantId;
		this.filling_time = filling_time;
		this.questionnaireId = questionnaireId;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFilloutId() {
		return filloutId;
	}

	public void setFilloutId(int filloutId) {
		this.filloutId = filloutId;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public LocalDateTime getFilling_time() {
		return filling_time;
	}

	public void setFilling_time(LocalDateTime filling_time) {
		this.filling_time = filling_time;
	}

	public UUID getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(UUID questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

}

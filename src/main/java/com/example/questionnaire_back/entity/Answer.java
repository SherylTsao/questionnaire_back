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
@Table(name = "answer")
public class Answer {

	@GeneratedValue(strategy = GenerationType.IDENTITY) // 流水編號遞增
	@Id
	@Column(name = "answer_id")
	private int answerId;// 打建構方法可省略,因為系統自動遞增,所以自己輸入的無效

	@Type(type = "uuid-char")
	@Column(name = "questionnaire_id")
	private UUID questionnaireId;
	
	@Column(name = "question_id")
	private int questionId;
	
	@Column(name = "participant_id")
	private int participantId;
	
	@Column(name = "option_id")
	private int optionId;
	
	@Column(name = "answer_text")
	private String answerText;

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public UUID getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(UUID questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
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
	
	
	
}

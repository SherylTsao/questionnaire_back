package com.example.questionnaire_back.vo;

public class QuestionAndQoptionsVo {
	private String text;
	private String type;
	private boolean required;
	private String qOptions;

	public QuestionAndQoptionsVo() {
		super();
	}

	public QuestionAndQoptionsVo(String text, String type, boolean required, String qOptions) {
		super();
		this.text = text;
		this.type = type;
		this.required = required;
		this.qOptions = qOptions;
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

	public String getqOptions() {
		return qOptions;
	}

	public void setqOptions(String qOptions) {
		this.qOptions = qOptions;
	}

}

package com.example.questionnaire_back.vo;

public class QuestionAndQoptionsVo {

	private String text; //問卷表的問題內容
	private String type; //問卷表的問題類型
	private boolean required;//問卷表的是否必填
	private String qOptions;//選項表的選項內容

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

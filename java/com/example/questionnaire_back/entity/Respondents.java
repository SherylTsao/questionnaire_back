package com.example.questionnaire_back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "respondents")
public class Respondents {
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 流水編號遞增
	@Id
	@Column(name = "participant_id")
	private int participantId;// 打建構方法可省略,因為系統自動遞增,所以自己輸入的無效

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private int age;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	public Respondents() {
		super();
	}

	public Respondents(String name, String email, int age, String gender, String address, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
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

}

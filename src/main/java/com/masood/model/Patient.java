package com.masood.model;

import java.time.LocalDate;

public class Patient {
	private String patient_Id;
	private User user_id;
	private LocalDate date_of_birth;
	private String gender;
	private String blood_group;

	public Patient() {
	}

	public Patient(User user_id, LocalDate date_of_birth, String gender, String blood_group) 
	{
		this.user_id = user_id;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.blood_group = blood_group;
	}

	public String getPatient_Id() {
		return patient_Id;
	}

	public void setPatient_Id(String patient_Id) {
		this.patient_Id = patient_Id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	@Override
	public String toString() {
		return "Patient [patient_Id=" + patient_Id + ", user_id=" + user_id + ", date_of_birth=" + date_of_birth
				+ ", gender=" + gender + ", blood_group=" + blood_group + "]";
	}

}

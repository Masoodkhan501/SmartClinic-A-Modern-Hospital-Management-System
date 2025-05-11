package com.masood.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity(name="patient")
@Table(name="patient_details")
public class Patient {
	@Id
	@Column(name="p_id")
	private String patient_Id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user_id;
	
	@Column(name="date_of_birth")
	private LocalDate date_of_birth;
	
	@Column(name="p_gender")
	private String gender;
	
	@Column(name="p_b_group")
	private String blood_group;
	
	@OneToMany(mappedBy = "patient_id")
	private List<PatientBills> bills;

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

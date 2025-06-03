package com.masood.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "patient")
@Table(name = "patient_details")
public class Patient {
	@Id
	@Column(name = "p_id")
	private String patient_Id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user_id;

	@Column(name = "date_of_birth")
	private LocalDate date_of_birth;

	@Column(name = "p_gender")
	private String gender;

	@Column(name = "p_b_group")
	private String blood_group;

	public Patient() {
	}

	public Patient(String patient_Id, User user_id, LocalDate date_of_birth, String gender, String blood_group) {
		this.patient_Id = patient_Id;
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

	public String toString() {
		return "Patient [patient_Id=" + patient_Id + ", user_id=" + user_id + ", date_of_birth=" + date_of_birth
				+ ", gender=" + gender + ", blood_group=" + blood_group + "]";
	}

	public int hashCode() {
		return Objects.hash(blood_group, date_of_birth, gender, patient_Id, user_id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(blood_group, other.blood_group) && Objects.equals(date_of_birth, other.date_of_birth)
				&& Objects.equals(gender, other.gender) && Objects.equals(patient_Id, other.patient_Id)
				&& Objects.equals(user_id, other.user_id);
	}

}

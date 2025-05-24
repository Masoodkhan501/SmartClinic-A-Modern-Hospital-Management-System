package com.masood.DTO;

import com.masood.model.Patient;
import com.masood.model.User;

public class PatientDTO {
	private Patient patient;
	private User user;

	public PatientDTO() {
	}

	public PatientDTO(Patient patient, User user) {
		this.patient = patient;
		this.user = user;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return "PatientDTO [" + (patient != null ? "patient=" + patient + ", " : "")
				+ (user != null ? "user=" + user : "") + "]";
	}

}

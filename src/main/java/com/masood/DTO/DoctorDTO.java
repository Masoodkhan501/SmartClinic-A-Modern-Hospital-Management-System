package com.masood.DTO;

import com.masood.model.Doctor;
import com.masood.model.User;

public class DoctorDTO {
	private Doctor doctor;
	private User user;

	public DoctorDTO(Doctor doctor, User user) {
		this.doctor = doctor;
		this.user = user;
	}

	public DoctorDTO() {
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "DoctorDTO [" + (doctor != null ? "doctor=" + doctor + ", " : "") + (user != null ? "user=" + user : "")
				+ "]";
	}

}

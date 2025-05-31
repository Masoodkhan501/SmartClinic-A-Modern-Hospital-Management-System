package com.masood.DTO;

import com.masood.model.Appointment;
import com.masood.model.Patient;
import com.masood.model.User;

public class AppointmentDTO {
	private Patient patient;
	private User user;
	private Appointment appt;

	public AppointmentDTO() {
	}

	public AppointmentDTO(Patient patient, User user, Appointment appt) {
		this.patient = patient;
		this.user = user;
		this.appt = appt;
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

	public Appointment getAppt() {
		return appt;
	}

	public void setAppt(Appointment appt) {
		this.appt = appt;
	}

	public String toString() {
		return "AppointmentDTO [" + (patient != null ? "patient=" + patient + ", " : "")
				+ (user != null ? "user=" + user + ", " : "") + (appt != null ? "appt=" + appt + ", " : "")
				+ "]";
	}

}

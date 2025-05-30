package com.masood.DTO;

import com.masood.model.Appointment;
import com.masood.model.Message;
import com.masood.model.Patient;
import com.masood.model.User;
import com.masood.model.priscription;

public class PatientDTO {
	private Patient patient;
	private User user;
	private Message msg;
	private Appointment appoint;
	private priscription pris;

	public PatientDTO() {
	}

	public PatientDTO(Patient patient, User user) {
		this.patient = patient;
		this.user = user;
	}

	public PatientDTO(Patient patient, User user, Message msg, Appointment appoint,
			priscription pris) {
		this.patient = patient;
		this.user = user;
		this.msg = msg;
		this.appoint = appoint;
		this.pris = pris;
	}

	public Message getMsg() {
		return msg;
	}

	public priscription getPris() {
		return pris;
	}

	public void setPris(priscription pris) {
		this.pris = pris;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Appointment getAppoint() {
		return appoint;
	}

	public void setAppoint(Appointment appoint) {
		this.appoint = appoint;
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
				+ (user != null ? "user=" + user + ", " : "") + (msg != null ? "msg=" + msg + ", " : "")
				+ (appoint != null ? "appoint=" + appoint + ", " : "")
				+ (pris != null ? "pris=" + pris : "") + "]";
	}

}

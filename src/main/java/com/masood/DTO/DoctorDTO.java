package com.masood.DTO;

import com.masood.model.Appointment;
import com.masood.model.Doctor;
import com.masood.model.Message;
import com.masood.model.PatientBills;
import com.masood.model.User;
import com.masood.model.priscription;

public class DoctorDTO {
	private Doctor doctor;
	private User user;
	private Message msg;
	private Appointment appoint;
	private PatientBills pbills;
	private priscription pris;

	public DoctorDTO(Doctor doctor, User user) {
		this.doctor = doctor;
		this.user = user;
	}
	
	public DoctorDTO(Doctor doctor, User user, Message msg, Appointment appoint, PatientBills pbills,
			priscription pris) {
		this.doctor = doctor;
		this.user = user;
		this.msg = msg;
		this.appoint = appoint;
		this.pbills = pbills;
		this.pris = pris;
	}
	
	public Message getMsg() {
		return msg;
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

	public PatientBills getPbills() {
		return pbills;
	}

	public void setPbills(PatientBills pbills) {
		this.pbills = pbills;
	}

	public priscription getPris() {
		return pris;
	}

	public void setPris(priscription pris) {
		this.pris = pris;
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

	public String toString() {
		return "DoctorDTO [" + (doctor != null ? "doctor=" + doctor + ", " : "")
				+ (user != null ? "user=" + user + ", " : "") + (msg != null ? "msg=" + msg + ", " : "")
				+ (appoint != null ? "appoint=" + appoint + ", " : "")
				+ (pbills != null ? "pbills=" + pbills + ", " : "") + (pris != null ? "pris=" + pris : "") + "]";
	}

}

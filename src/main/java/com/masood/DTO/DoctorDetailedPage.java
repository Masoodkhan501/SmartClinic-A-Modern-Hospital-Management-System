package com.masood.DTO;

import java.util.List;

import com.masood.model.Appointment;
import com.masood.model.Message;
import com.masood.model.Patient;

public class DoctorDetailedPage {
	private List<Appointment> todayAppointments;
	private List<Appointment> upcomingAppointments;
	private List<Message> unreadMessages;
	private List<Patient> relatedPatients;

	public DoctorDetailedPage(List<Appointment> todayAppointments, List<Appointment> upcomingAppointments,
			List<Message> unreadMessages, List<Patient> relatedPatients) {
		this.todayAppointments = todayAppointments;
		this.upcomingAppointments = upcomingAppointments;
		this.unreadMessages = unreadMessages;
		this.relatedPatients = relatedPatients;
	}

	public DoctorDetailedPage() {
	}

	public List<Appointment> getTodayAppointments() {
		return todayAppointments;
	}

	public void setTodayAppointments(List<Appointment> todayAppointments) {
		this.todayAppointments = todayAppointments;
	}

	public List<Appointment> getUpcomingAppointments() {
		return upcomingAppointments;
	}

	public void setUpcomingAppointments(List<Appointment> upcomingAppointments) {
		this.upcomingAppointments = upcomingAppointments;
	}

	public List<Message> getUnreadMessages() {
		return unreadMessages;
	}

	public void setUnreadMessages(List<Message> unreadMessages) {
		this.unreadMessages = unreadMessages;
	}

	public List<Patient> getRelatedPatients() {
		return relatedPatients;
	}

	public void setRelatedPatients(List<Patient> relatedPatients) {
		this.relatedPatients = relatedPatients;
	}

	@Override
	public String toString() {
		return "DoctorDetailedPage ["
				+ (todayAppointments != null ? "todayAppointments=" + todayAppointments + ", " : "")
				+ (upcomingAppointments != null ? "upcomingAppointments=" + upcomingAppointments + ", " : "")
				+ (unreadMessages != null ? "unreadMessages=" + unreadMessages + ", " : "")
				+ (relatedPatients != null ? "relatedPatients=" + relatedPatients : "") + "]";
	}

}

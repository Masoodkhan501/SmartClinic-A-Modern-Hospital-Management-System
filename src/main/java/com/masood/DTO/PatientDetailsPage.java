package com.masood.DTO;

import java.util.List;

import com.masood.model.Appointment;
import com.masood.model.Message;

public class PatientDetailsPage {
	private List<Appointment> appointments;
	private List<Appointment> upcomingappointments;
	private List<Message> unreadMessages;
	private List<Appointment> unpaidappointments;
	private List<Message> allrecievedMsg;
	private List<Message> allsendMsg;

	public PatientDetailsPage() {
	}

	public PatientDetailsPage(List<Appointment> appointments, List<Appointment> upcomingappointments,
			List<Message> unreadMessages, List<Appointment> unpaidappointments, List<Message> allrecievedMsg,
			List<Message> allsendMsg) {
		this.appointments = appointments;
		this.upcomingappointments = upcomingappointments;
		this.unreadMessages = unreadMessages;
		this.unpaidappointments = unpaidappointments;
		this.allrecievedMsg = allrecievedMsg;
		this.allsendMsg = allsendMsg;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	

	public List<Message> getAllrecievedMsg() {
		return allrecievedMsg;
	}

	public void setAllrecievedMsg(List<Message> allrecievedMsg) {
		this.allrecievedMsg = allrecievedMsg;
	}

	public List<Message> getAllsendMsg() {
		return allsendMsg;
	}

	public void setAllsendMsg(List<Message> allsendMsg) {
		this.allsendMsg = allsendMsg;
	}

	public List<Appointment> getUpcomingappointments() {
		return upcomingappointments;
	}

	public void setUpcomingappointments(List<Appointment> upcomingappointments) {
		this.upcomingappointments = upcomingappointments;
	}
	
	public List<Appointment> getUnpaidappointments() {
		return unpaidappointments;
	}

	public void setUnpaidappointments(List<Appointment> unpaidappointments) {
		this.unpaidappointments = unpaidappointments;
	}

	public List<Message> getUnreadMessages() {
		return unreadMessages;
	}

	public void setUnreadMessages(List<Message> unreadMessages) {
		this.unreadMessages = unreadMessages;
	}

	public String toString() {
		return "PatientDetailsPage [" + (appointments != null ? "appointments=" + appointments + ", " : "")
				+ (upcomingappointments != null ? "upcomingappointments=" + upcomingappointments + ", " : "")
				+ (unreadMessages != null ? "unreadMessages=" + unreadMessages + ", " : "")
				+ (unpaidappointments != null ? "unpaidappointments=" + unpaidappointments + ", " : "")
				+ (allrecievedMsg != null ? "allrecievedMsg=" + allrecievedMsg + ", " : "")
				+ (allsendMsg != null ? "allsendMsg=" + allsendMsg : "") + "]";
	}

}

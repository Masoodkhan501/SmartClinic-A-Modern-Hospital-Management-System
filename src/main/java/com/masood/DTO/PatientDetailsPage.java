package com.masood.DTO;

import java.util.List;

import com.masood.model.Appointment;
import com.masood.model.Message;
import com.masood.model.priscription;

public class PatientDetailsPage {
	private List<Appointment> appointments;
	private List<Appointment> upcomingappointments;
	private List<Message> unreadMessages;
	private List<Appointment> unpaidappointments;
	private List<Message> allrecievedMsg;
	private List<Message> allsendMsg;
	private List<priscription> allpriscription;

	public PatientDetailsPage() {
	}

	public PatientDetailsPage(List<Appointment> appointments, List<Appointment> upcomingappointments,
			List<Message> unreadMessages, List<Appointment> unpaidappointments, List<Message> allrecievedMsg,
			List<Message> allsendMsg, List<priscription> allpriscription) {
		this.appointments = appointments;
		this.upcomingappointments = upcomingappointments;
		this.unreadMessages = unreadMessages;
		this.unpaidappointments = unpaidappointments;
		this.allrecievedMsg = allrecievedMsg;
		this.allsendMsg = allsendMsg;
		this.allpriscription = allpriscription;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public List<priscription> getAllpriscription() {
		return allpriscription;
	}

	public void setAllpriscription(List<priscription> allpriscription) {
		this.allpriscription = allpriscription;
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
				+ (allsendMsg != null ? "allsendMsg=" + allsendMsg + ", " : "")
				+ (allpriscription != null ? "allpriscription=" + allpriscription : "") + "]";
	}

}

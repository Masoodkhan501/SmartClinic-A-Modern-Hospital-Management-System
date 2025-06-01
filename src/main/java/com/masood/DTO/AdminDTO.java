package com.masood.DTO;

import java.util.List;

import com.masood.model.Appointment;
import com.masood.model.AppointmentHistory;
import com.masood.model.Doctor;
import com.masood.model.Message;
import com.masood.model.MessageHistory;
import com.masood.model.Patient;
import com.masood.model.User;
import com.masood.model.priscription;

public class AdminDTO {
	private User user;
	private List<Patient> l_p;
	private List<Doctor> l_d;
	private List<Appointment> allAppointment;
	private List<Appointment> pendingAppointments;
	private List<priscription> allprescription;
	private List<Message> allmsg;
	private List<AppointmentHistory> allappthis;
	private List<MessageHistory> allMsgHis;
	private List<Message> allunreadMsg;
	private List<Message> allsendMsg;
	private List<Message> allrecieveMsg;

	public AdminDTO() {
	}

	public AdminDTO(User user, List<Patient> l_p, List<Doctor> l_d, List<Appointment> allAppointment,
			List<Appointment> pendingAppointments, List<priscription> allprescription, List<Message> allmsg,
			List<AppointmentHistory> allappthis, List<MessageHistory> allMsgHis, List<Message> allunreadMsg,
			List<Message> allsendMsg, List<Message> allrecieveMsg) {
		this.user = user;
		this.l_p = l_p;
		this.l_d = l_d;
		this.allAppointment = allAppointment;
		this.pendingAppointments = pendingAppointments;
		this.allprescription = allprescription;
		this.allmsg = allmsg;
		this.allappthis = allappthis;
		this.allMsgHis = allMsgHis;
		this.allunreadMsg = allunreadMsg;
		this.allsendMsg = allsendMsg;
		this.allrecieveMsg = allrecieveMsg;
	}

	public AdminDTO(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public List<Message> getAllmsg() {
		return allmsg;
	}

	public void setAllmsg(List<Message> allmsg) {
		this.allmsg = allmsg;
	}

	
	
	public List<Message> getAllunreadMsg() {
		return allunreadMsg;
	}

	public void setAllunreadMsg(List<Message> allunreadMsg) {
		this.allunreadMsg = allunreadMsg;
	}

	public List<Message> getAllsendMsg() {
		return allsendMsg;
	}

	public void setAllsendMsg(List<Message> allsendMsg) {
		this.allsendMsg = allsendMsg;
	}

	public List<Message> getAllrecieveMsg() {
		return allrecieveMsg;
	}

	public void setAllrecieveMsg(List<Message> allrecieveMsg) {
		this.allrecieveMsg = allrecieveMsg;
	}

	public List<AppointmentHistory> getAllappthis() {
		return allappthis;
	}

	public void setAllappthis(List<AppointmentHistory> allappthis) {
		this.allappthis = allappthis;
	}

	public List<MessageHistory> getAllMsgHis() {
		return allMsgHis;
	}

	public void setAllMsgHis(List<MessageHistory> allMsgHis) {
		this.allMsgHis = allMsgHis;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Patient> getL_p() {
		return l_p;
	}

	public void setL_p(List<Patient> l_p) {
		this.l_p = l_p;
	}

	public List<Doctor> getL_d() {
		return l_d;
	}

	public void setL_d(List<Doctor> l_d) {
		this.l_d = l_d;
	}

	public List<Appointment> getAllAppointment() {
		return allAppointment;
	}

	public void setAllAppointment(List<Appointment> allAppointment) {
		this.allAppointment = allAppointment;
	}

	public List<Appointment> getPendingAppointments() {
		return pendingAppointments;
	}

	public void setPendingAppointments(List<Appointment> pendingAppointments) {
		this.pendingAppointments = pendingAppointments;
	}

	public List<priscription> getAllprescription() {
		return allprescription;
	}

	public void setAllprescription(List<priscription> allprescription) {
		this.allprescription = allprescription;
	}

	public String toString() {
		return "AdminDTO [" + (user != null ? "user=" + user + ", " : "") + (l_p != null ? "l_p=" + l_p + ", " : "")
				+ (l_d != null ? "l_d=" + l_d + ", " : "")
				+ (allAppointment != null ? "allAppointment=" + allAppointment + ", " : "")
				+ (pendingAppointments != null ? "pendingAppointments=" + pendingAppointments + ", " : "")
				+ (allprescription != null ? "allprescription=" + allprescription + ", " : "")
				+ (allmsg != null ? "allmsg=" + allmsg + ", " : "")
				+ (allappthis != null ? "allappthis=" + allappthis + ", " : "")
				+ (allMsgHis != null ? "allMsgHis=" + allMsgHis + ", " : "")
				+ (allunreadMsg != null ? "allunreadMsg=" + allunreadMsg + ", " : "")
				+ (allsendMsg != null ? "allsendMsg=" + allsendMsg + ", " : "")
				+ (allrecieveMsg != null ? "allrecieveMsg=" + allrecieveMsg : "") + "]";
	}

}

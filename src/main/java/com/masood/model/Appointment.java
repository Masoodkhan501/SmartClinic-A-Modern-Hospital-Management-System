package com.masood.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "appointment")
@Table(name = "appointment_details")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private Long app_id;

	@ManyToOne
	@JoinColumn(name = "doc_id")
	private Doctor d_id;

	@ManyToOne
	@JoinColumn(name = "p_id")
	private Patient p_id;

	@ManyToOne
	@JoinColumn(name = "disease_id")
	private Disease disease;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_appointment")
	private Date dateofAppointment;

	@Enumerated(EnumType.STRING)
	@Column(name = "ap_status")
	private Appointmentstatus status;

	@Column(name = "notes")
	private String notes;

	public Appointment() {
	}

	public Appointment(Doctor d_id, Patient p_id, Disease disease, Date date_of_appointment,
			Appointmentstatus status, String notes) {
		this.d_id = d_id;
		this.p_id = p_id;
		this.disease = disease;
		this.dateofAppointment = date_of_appointment;
		this.status = status;
		this.notes = notes;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public Doctor getDoctor() {
		return d_id;
	}

	public void setD_id(Doctor d_id) {
		this.d_id= d_id;
	}

	public Patient getPatient() {
		return p_id;
	}

	public void setP_id(Patient p_id) {
		this.p_id = p_id;
	}

	public Date getdateofAppointment() {
		return dateofAppointment;
	}

	public void setdateofAppointment(Date date_of_appointment) {
		this.dateofAppointment = date_of_appointment;
	}

	public Appointmentstatus getStatus() {
		return status;
	}

	public void setStatus(Appointmentstatus status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public String toString() {
		return "Appointment [app_id=" + app_id + ", d_id=" + d_id + ", p_id=" + p_id + ", disease=" + disease
				+ ", date_of_appointment=" + dateofAppointment + ", status=" + status + ", notes=" + notes + "]";
	}

}

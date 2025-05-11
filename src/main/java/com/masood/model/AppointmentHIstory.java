package com.masood.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "appointmenthistory")
@Table(name = "appointment_history")
public class AppointmentHIstory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "h_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appoint_id;

	@Temporal(TemporalType.DATE)
	@Column(name = "notes_modifydate")
	private Date date_changed;

	@Column(name = "oldNotes")
	private String oldNotes;

	@Column(name = "Changedby")
	private String changedBy;

	public AppointmentHIstory() {
	}

	public AppointmentHIstory(Appointment appoint_id, Date date_changed, String oldNotes, String changedBy) {
		this.appoint_id = appoint_id;
		this.date_changed = date_changed;
		this.oldNotes = oldNotes;
		this.changedBy = changedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Appointment getAppoint_id() {
		return appoint_id;
	}

	public void setAppoint_id(Appointment appoint_id) {
		this.appoint_id = appoint_id;
	}

	public Date getDate_changed() {
		return date_changed;
	}

	public void setDate_changed(Date date_changed) {
		this.date_changed = date_changed;
	}

	public String getOldNotes() {
		return oldNotes;
	}

	public void setOldNotes(String oldNotes) {
		this.oldNotes = oldNotes;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

}

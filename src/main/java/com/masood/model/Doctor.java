package com.masood.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "Doctor")
@Table(name = "Doctor_detials")
public class Doctor {
	@Id
	@Column(name = "doc_id")
	private String doc_id;

	@OneToOne
	@JoinColumn(name = "user_Id")
	private User user_id;

	@ManyToMany
	@JoinTable(name = "Doctor_specialization", joinColumns = @JoinColumn(name = "Doctor_id"), inverseJoinColumns = @JoinColumn(name = "specialization_id"))
	private List<Specialized> specializations;

	@Column(name = "years_of_experience")
	private byte expreince;

	@Enumerated(EnumType.STRING)
	@Column(name = "doc_status")
	private Status status;

	@OneToMany(mappedBy = "doctor")
	private DoctorBills payment;

	public Doctor() {
	}

	public Doctor(String doc_id, User user_id, List<Specialized> specializations, byte expreince, Status status,
			DoctorBills payment) {
		this.doc_id = doc_id;
		this.user_id = user_id;
		this.specializations = specializations;
		this.expreince = expreince;
		this.status = status;
		this.payment = payment;
	}

	public List<Specialized> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(List<Specialized> specializations) {
		this.specializations = specializations;
	}

	public DoctorBills getPayment() {
		return payment;
	}

	public void setPayment(DoctorBills payment) {
		this.payment = payment;
	}

	public String getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public List<Specialized> getSpecialized_at() {
		return specializations;
	}

	public void setSpecialized_at(List<Specialized> specialized_at) {
		this.specializations = specialized_at;
	}

	public byte getExpreince() {
		return expreince;
	}

	public void setExpreince(byte expreince) {
		this.expreince = expreince;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String toString() {
		return "Doctor [doc_id=" + doc_id + ", user_id=" + user_id + ", specialized_at=" + specializations
				+ ", expreince=" + expreince + ", status=" + status + "]";
	}

}

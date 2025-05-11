package com.masood.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "DoctorBills")
@Table(name = "Doctor_bills")
public class DoctorBills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="payment_Date")
	private LocalDateTime paymentDate;
	
	@Column(name="payment_method")
	private String paymentMethod;

	@Column(name="notes")
	private String notes;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	public DoctorBills(BigDecimal amount, LocalDateTime paymentDate, String paymentMethod, String notes,
			Doctor doctor) {
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.notes = notes;
		this.doctor = doctor;
	}

	public DoctorBills() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String toString() {
		return "DoctorBills [id=" + id + ", amount=" + amount + ", paymentDate=" + paymentDate + ", paymentMethod="
				+ paymentMethod + ", notes=" + notes + ", doctor=" + doctor + "]";
	}

}

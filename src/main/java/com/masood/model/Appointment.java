package com.masood.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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

	@Column(name = "appointment_fee")
	private Double treatmentFee;

	@Column(name = "operation_fee")
	private Double operationFee;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_operation")
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dateOfOperation;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "operation_required")
    private OperationNeeded operationRequired;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_appointment")
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dateofAppointment;

	@Enumerated(EnumType.STRING)
	@Column(name = "ap_status")
	private Appointmentstatus status;

	@Column(name = "notes")
	private String notes;

	public Appointment() {
	}

	public Appointment(Doctor d_id, Patient p_id, Date date_of_appointment,
	        Appointmentstatus status, String notes, Date dateOfOperation) {
	    this.d_id = d_id;
	    this.p_id = p_id;
	    this.dateofAppointment = date_of_appointment;
	    this.status = status;
	    this.notes = notes;
	    this.dateOfOperation = dateOfOperation;
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
	public Date getDateOfOperation() {
	    return dateOfOperation;
	}
	
	public Double getTreatmentFee() {
		return treatmentFee;
	}

	public void setTreatmentFee(Double treatmentFee) {
		this.treatmentFee = treatmentFee;
	}

	public Double getOperationFee() {
	    return operationFee;
	}

	public void setOperationFee(Double operationFee) {
	    this.operationFee = operationFee;
	}
	
	public void setDateOfOperation(Date dateOfOperation) {
	    this.dateOfOperation = dateOfOperation;
	}

	public void setD_id(Doctor d_id) {
		this.d_id= d_id;
	}

	public OperationNeeded getOperationRequired() {
		return operationRequired;
	}

	public void setOperationRequired(OperationNeeded operationRequired) {
		this.operationRequired = operationRequired;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getDateofAppointment() {
		return dateofAppointment;
	}

	public void setDateofAppointment(Date dateofAppointment) {
		this.dateofAppointment = dateofAppointment;
	}

	public Doctor getD_id() {
		return d_id;
	}

	public Patient getP_id() {
		return p_id;
	}
	
	public Patient getPatient() {
		return p_id;
	}

	public void setP_id(Patient p_id) {
		this.p_id = p_id;
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

	public String toString() {
		return "Appointment [" + (app_id != null ? "app_id=" + app_id + ", " : "")
				+ (d_id != null ? "d_id=" + d_id + ", " : "") + (p_id != null ? "p_id=" + p_id + ", " : "")
				+ (treatmentFee != null ? "treatmentFee=" + treatmentFee + ", " : "")
				+ (operationFee != null ? "operationFee=" + operationFee + ", " : "")
				+ (dateOfOperation != null ? "dateOfOperation=" + dateOfOperation + ", " : "")
				+ (operationRequired != null ? "operationRequired=" + operationRequired + ", " : "")
				+ (paymentStatus != null ? "paymentStatus=" + paymentStatus + ", " : "")
				+ (dateofAppointment != null ? "dateofAppointment=" + dateofAppointment + ", " : "")
				+ (status != null ? "status=" + status + ", " : "") + (notes != null ? "notes=" + notes : "") + "]";
	}

}

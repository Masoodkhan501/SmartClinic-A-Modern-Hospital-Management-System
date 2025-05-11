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

@Entity(name="bills")
@Table(name="patient_bills")
public class PatientBills 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patientBill_id")
	private Long patientBill_id;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient_id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="billdate")
	private Date billdate;
	
	@Column(name="consultancy_fees")
	private Double consultancyFees;
	
	@Column(name="treatment_fees")
	private Double treatmentFees;
	
	@Column(name="medication_fees")
	private Double medicationFees;
	
	@Column(name="room_fees")
	private Double roomcharges;
	
	@Column(name="lab_fees")
	private Double labcharges;
	
	@Column(name="discount_percentage")
	private Double discount;
	
	@Column(name="Total_charges")
	private Double total_charges;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_status")
	private PaymentStatus pay_status;
}

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

@Entity(name="Patientbills")
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
	

	public PatientBills() {
	}

	public PatientBills(Patient patient_id, Date billdate, Double consultancyFees, Double treatmentFees,
			Double medicationFees, Double roomcharges, Double labcharges, Double discount, Double total_charges,
			PaymentStatus pay_status) {
		this.patient_id = patient_id;
		this.billdate = billdate;
		this.consultancyFees = consultancyFees;
		this.treatmentFees = treatmentFees;
		this.medicationFees = medicationFees;
		this.roomcharges = roomcharges;
		this.labcharges = labcharges;
		this.discount = discount;
		this.total_charges = total_charges;
		this.pay_status = pay_status;
	}

	public Long getPatientBill_id() {
		return patientBill_id;
	}

	public void setPatientBill_id(Long patientBill_id) {
		this.patientBill_id = patientBill_id;
	}

	public Patient getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Patient patient_id) {
		this.patient_id = patient_id;
	}

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public Double getConsultancyFees() {
		return consultancyFees;
	}

	public void setConsultancyFees(Double consultancyFees) {
		this.consultancyFees = consultancyFees;
	}

	public Double getTreatmentFees() {
		return treatmentFees;
	}

	public void setTreatmentFees(Double treatmentFees) {
		this.treatmentFees = treatmentFees;
	}

	public Double getMedicationFees() {
		return medicationFees;
	}

	public void setMedicationFees(Double medicationFees) {
		this.medicationFees = medicationFees;
	}

	public Double getRoomcharges() {
		return roomcharges;
	}

	public void setRoomcharges(Double roomcharges) {
		this.roomcharges = roomcharges;
	}

	public Double getLabcharges() {
		return labcharges;
	}

	public void setLabcharges(Double labcharges) {
		this.labcharges = labcharges;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotal_charges() {
		return total_charges;
	}

	public void setTotal_charges(Double total_charges) {
		this.total_charges = total_charges;
	}

	public PaymentStatus getPay_status() {
		return pay_status;
	}

	public void setPay_status(PaymentStatus pay_status) {
		this.pay_status = pay_status;
	}


	public String toString() {
	    StringBuilder sb = new StringBuilder("PatientBills [");

	    if (patientBill_id != null) sb.append("patientBill_id=").append(patientBill_id).append(", ");
	    if (patient_id != null) sb.append("patient_id=").append(patient_id).append(", ");
	    if (billdate != null) sb.append("billdate=").append(billdate).append(", ");
	    if (consultancyFees != null) sb.append("consultancyFees=").append(consultancyFees).append(", ");
	    if (treatmentFees != null) sb.append("treatmentFees=").append(treatmentFees).append(", ");
	    if (medicationFees != null) sb.append("medicationFees=").append(medicationFees).append(", ");
	    if (roomcharges != null) sb.append("roomcharges=").append(roomcharges).append(", ");
	    if (labcharges != null) sb.append("labcharges=").append(labcharges).append(", ");
	    if (discount != null) sb.append("discount=").append(discount).append(", ");
	    if (total_charges != null) sb.append("total_charges=").append(total_charges).append(", ");
	    if (pay_status != null) sb.append("pay_status=").append(pay_status).append(", ");

	    // Remove trailing comma and space if present
	    int length = sb.length();
	    if (sb.lastIndexOf(", ") == length - 2) {
	        sb.setLength(length - 2);
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
}

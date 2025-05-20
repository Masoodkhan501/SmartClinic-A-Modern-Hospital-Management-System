package com.masood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name="priscription")
@Table(name="priscription_details")
public class priscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pri_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="appoint_id")
	private Appointment appointid;
	
	@Column(name="diagnosis")
	private String diagnosis;
	
	@Column(name="medicines")
	private String medicines;
	
	@Column(name="doctorsAdvice")
	private String advice;

	public priscription() {
	}

	public priscription(Appointment appoint_id, String diagnosis, String medicines, String advice) {
		this.appointid = appoint_id;
		this.diagnosis = diagnosis;
		this.medicines = medicines;
		this.advice = advice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Appointment getAppointid() {
		return appointid;
	}

	public void setAppointid(Appointment appoint_id) {
		this.appointid = appoint_id;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedicines() {
		return medicines;
	}

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String toString() {
		return "priscription [id=" + id + ", appoint_id=" + appointid + ", diagnosis=" + diagnosis + ", medicines="
				+ medicines + ", advice=" + advice + "]";
	}

}

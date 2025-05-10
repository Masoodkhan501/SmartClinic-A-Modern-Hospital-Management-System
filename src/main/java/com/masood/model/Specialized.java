package com.masood.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "specialization")
@Table(name = "specialization")
@Component
public class Specialized {
	@Id
	@Column(name = "specialization_id")
	private String id;
	@Column(name = "specialization_in")
	private String specialization;
	@ManyToMany(mappedBy = "specializations")
	private List<Doctor> specilist;

	public Specialized() {
	}

	public Specialized(String id, String specialization, List<Doctor> specilist) {
		this.id = id;
		this.specialization = specialization;
		this.specilist = specilist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Doctor> getSpecilist() {
		return specilist;
	}

	public void setSpecilist(List<Doctor> specilist) {
		this.specilist = specilist;
	}

	public String toString() {
		return "Specialized [id=" + id + ", specialization=" + specialization + ", specilist=" + specilist + "]";
	}

}

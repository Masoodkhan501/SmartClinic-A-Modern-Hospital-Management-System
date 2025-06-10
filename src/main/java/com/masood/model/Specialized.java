package com.masood.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "specialization")
@Table(name = "specialization")
public class Specialized {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "specialization_id")
	private Long id;
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
	@Override
	public int hashCode() {
		return Objects.hash(id, specialization, specilist);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specialized other = (Specialized) obj;
		return Objects.equals(id, other.id) && Objects.equals(specialization, other.specialization)
				&& Objects.equals(specilist, other.specilist);
	}
}

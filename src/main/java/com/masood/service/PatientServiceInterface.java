package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.Patient;

public interface PatientServiceInterface
{
	public Patient savePatient(Patient p);
	public Optional<Patient> getPatientById(String id);
	public List<Patient> getAllPatient();
	public void deletePatientById(String id);
	public Patient getByEmail(String email);
	public Patient getByNameLike(String name);
}

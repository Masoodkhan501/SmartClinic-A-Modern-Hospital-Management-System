package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.Patient;
import com.masood.model.User;

public interface PatientServiceInterface
{
	public Patient savePatient(Patient p,User u);
	public Optional<Patient> getPatientById(String id);
	public List<Patient> getAllPatient();
	public void deletePatientById(String id);
	public Patient getByEmail(String email);
	public Patient getByNameLike(String name);
}

package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.Doctor;

public interface DoctorServiceInterface 
{
	public Doctor saveDoctor(Doctor d);
	public Optional<Doctor> getDoctorById(Doctor d);
	public List<Doctor> getAllDoctor();
	public void DeleteDoctorById(Doctor d);
	public Doctor getByEmail(String email);
	public List<Doctor> getBySpecialization(String specialization);
	public List<Doctor> getTop5Doctors();
	public List<Doctor> getByNameAndSpecialization(String name, String specialization);
}

package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.Doctor;
import com.masood.model.User;

public interface DoctorServiceInterface 
{
	public Doctor saveDoctor(Doctor d,User u);
	public Optional<Doctor> getDoctorById(String id);
	public List<Doctor> getAllDoctor();
	public void DeleteDoctorById(String id);
	public Doctor getByEmail(String email);
	public List<Doctor> getBySpecialization(String specialization);
	public List<Doctor> getTop5Doctors();
	public List<Doctor> getByNameAndSpecialization(String name, String specialization);
}

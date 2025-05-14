package com.masood.service;

import java.util.List;

import com.masood.model.Doctor;

public interface DoctorServiceInterface 
{
	public Doctor saveDoctor(Doctor d);
	public Doctor getDoctorById(Doctor d);
	public List<Doctor> getAllDoctor();
	public int DeleteDoctorById(Doctor d);
	public Doctor getByEmail(Doctor d);
	public List<Doctor> getBySpecialization(String specialization);
	public List<Doctor> getTop5Doctors();
	public List<Doctor> getByNameAndSpecialization(String name, String specialization);
}

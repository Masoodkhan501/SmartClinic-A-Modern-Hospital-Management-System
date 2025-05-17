package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.priscription;

public interface PrescriptionInterface 
{
	public priscription savePriscription(priscription p);
	public Optional<priscription> getByid(Long id);
	public List<priscription> getAllPriscription();
	public void deletePriscriptionById(Long id);
	public List<priscription> getByDoctorId(String id);
	public List<priscription> getByPatientId(String id);
	public List<priscription> getByDoctorName(String name);
	public List<priscription> getByPatientName(String name);
	public Optional<priscription> getByAppointmentId(Long id);
}

package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.priscription;
import com.masood.repository.PriscriptionRepo;
@Service("PriscriptionService")
public class PriscriptionServiceImpl implements PrescriptionInterface
{
	@Autowired
	private PriscriptionRepo pr;
	
	public priscription savePriscription(priscription p) 
	{
		return pr.save(p);
	}

	public Optional<priscription> getByid(Long id) {
		return pr.findById(id);
	}

	public List<priscription> getAllPriscription() {
		return pr.findAll();
	}

	public void deletePriscriptionById(Long id) {
		pr.deleteById(id);
	}

	public List<priscription> getByDoctorId(String id) {
		return pr.findByDoctorId(id);
	}

	public List<priscription> getByPatientId(String id) {
		return pr.findByPatientId(id);
	}

	public List<priscription> getByDoctorName(String name) {
		return pr.findByDoctorName(name);
	}

	public List<priscription> getByPatientName(String name) {
		return pr.findByPatientName(name);
	}

	public Optional<priscription> getByAppointmentId(Long id) {
		return pr.findByAppointmentId(id);
	}

}

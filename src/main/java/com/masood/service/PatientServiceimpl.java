package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Patient;
import com.masood.repository.patientRepo;
@Service("psi")
public class PatientServiceimpl implements PatientServiceInterface
{
	@Autowired
	private patientRepo pr;
	
	public Patient savePatient(Patient p) 
	{
		String lastEntry = pr.findLastEntry();
		String id = "";
		if (lastEntry != null) {
			String last3Digit = lastEntry.substring(2);
			Integer lastNumber = Integer.parseInt(last3Digit);
			lastNumber++;
			id = String.format("PAT%03d", lastNumber);
		} else {
			id = "PAT001";
		}
		p.setPatient_Id(id);
		Patient save = pr.save(p);
		return save;
	}

	public Optional<Patient> getPatientById(String id) 
	{
		return pr.findById(id);
	}

	public List<Patient> getAllPatient() 
	{
		return pr.findAll();
	}

	public void deletePatientById(String id)
	{
		pr.deleteById(id);
	}

	public Patient getByEmail(String email) 
	{
		return pr.findByEmail(email);
	}

	public Patient getByNameLike(String name)
	{
		return pr.findByNameLike(name);
	}

}

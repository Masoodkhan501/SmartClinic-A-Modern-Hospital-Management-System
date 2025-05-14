package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Doctor;
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
		d.setDoc_id(id);
		Patient save = pr.save(p);
		return save;
		return null;
	}

	@Override
	public Optional<Patient> getPatientById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatientById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Patient getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getByNameLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

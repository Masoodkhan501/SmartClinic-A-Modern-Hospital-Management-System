package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.PatientBills;
import com.masood.repository.PatientBillsRepo;
@Service("patientBillservice")
public class PatientBillsImpl implements PatientBillsInterface 
{
	@Autowired
	private PatientBillsRepo pbr;
	
	public PatientBills savePatientBills(PatientBills pb) {
		return pbr.save(pb);
	}

	public Optional<PatientBills> getById(Long id) {
		return pbr.findById(id);
	}

	public List<PatientBills> getAll() {
		return pbr.findAll();
	}

	public void deleteById(Long id) {
		pbr.deleteById(id);
	}

	public void DeleteAllbillsByPatientId(String id) {
		pbr.DeleteBillsByPatientId(id);
	}

	public List<PatientBills> getAllBillsByPatientId(String id) {
		return pbr.findByPatientId(id);
	}

	public List<PatientBills> getAllBillsByPatientName(String name) {
		return pbr.findBillsByPatientNameLike(name);
	}

}

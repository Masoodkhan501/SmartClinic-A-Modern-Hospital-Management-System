package com.masood.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.OperationNeeded;
import com.masood.model.Patient;
import com.masood.model.PaymentStatus;
import com.masood.model.User;
import com.masood.repository.patientRepo;

import jakarta.transaction.Transactional;

@Service("psi")
@Transactional
public class PatientServiceimpl implements PatientServiceInterface
{

	@Autowired
	private patientRepo pr;
	@Autowired
	private UserImpl ur;
	@Autowired
	private AppointmentService as;

	
	public Patient savePatient(Patient p,User u) 
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
		u.setCreatedAt();
		User save2 = ur.saveUser(u);
		p.setPatient_Id(id);
		p.setUser_id(save2);
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

	

	public Byte getPatientAge(Patient p) {
		LocalDate date_of_birth = p.getDate_of_birth();
		if (date_of_birth == null) {
	        return null; // or return 0 if you want a default value
	    }

	    LocalDate currentDate = LocalDate.now();
	    int age = Period.between(date_of_birth, currentDate).getYears();

	    return (byte) age;
	}

	public Double getTotalAmountofDueBills(Patient p) {
		return as.getByPatient(p.getPatient_Id()).stream()
				.filter(appt->appt.getPaymentStatus().equals(PaymentStatus.UNPAID))
				.mapToDouble(appt -> {
	                if (appt.getOperationRequired().equals(OperationNeeded.YES)) {
	                    return (appt.getTreatmentFee() != null ? appt.getTreatmentFee() : 0.0)
	                         + (appt.getOperationFee() != null ? appt.getOperationFee() : 0.0);
	                } else {
	                    return (appt.getTreatmentFee() != null ? appt.getTreatmentFee() : 0.0);
	                }
	            })
	            .sum();
	}
	
	

}

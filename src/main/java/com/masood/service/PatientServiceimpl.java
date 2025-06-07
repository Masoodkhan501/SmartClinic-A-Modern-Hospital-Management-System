package com.masood.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
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

	public String generateNewPatientId() {
	    String lastId = pr.findLastEntry(); // e.g., "PAT015" or null
	    int number = 0;

	    if (lastId != null && lastId.length() >= 6 && lastId.startsWith("PAT")) {
	        try {
	            String numPart = lastId.substring(3); // Extract "015"
	            number = Integer.parseInt(numPart);   // Convert to 15
	        } catch (NumberFormatException e) {
	            number = 0; // fallback if somehow broken data
	        }
	    }

	    int newNumber = number + 1;
	    return String.format("PAT%03d", newNumber); // e.g., "PAT016"
	}

	
	public Patient savePatient(Patient p,User u) 
	{
	    String newId = generateNewPatientId();
	    p.setPatient_Id(newId);
		u.setCreatedAt();
		User save2 = ur.saveUser(u);
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
				.filter(appt->Objects.equals(appt.getPaymentStatus(), PaymentStatus.UNPAID))
				.mapToDouble(appt -> {
	                if (Objects.equals(appt.getOperationRequired(), OperationNeeded.YES)) {
	                    return (appt.getTreatmentFee() != null ? appt.getTreatmentFee() : 0.0)
	                         + (appt.getOperationFee() != null ? appt.getOperationFee() : 0.0);
	                } else {
	                    return (appt.getTreatmentFee() != null ? appt.getTreatmentFee() : 0.0);
	                }
	            })
	            .sum();
	}
	
	

}

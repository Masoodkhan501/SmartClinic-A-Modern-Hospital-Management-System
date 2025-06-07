package com.masood.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Doctor;
import com.masood.model.Role;
import com.masood.model.Status;
import com.masood.model.User;
import com.masood.repository.DoctorRepoInterface;

import jakarta.transaction.Transactional;

@Service("Doctor_service")
@Transactional
public class DoctorSerivce implements DoctorServiceInterface {

	@Autowired
	private DoctorRepoInterface dr;
	@Autowired
	private UserImpl ur;
	
	public String generateNewDocid()
	{
		String LastId = dr.findLastEntry();
		int number  = 0;
		if(LastId != null && LastId.length()>=6 && LastId.startsWith("DOC"))
		{
			try {
				String numPart = LastId.substring(3);
				number = Integer.parseInt(numPart);
			}catch (NumberFormatException e) {
				number = 0;
			}
		}
		int newNumber = number + 1;
		return String.format("DOC%03d", newNumber);
	}
	
	public Doctor saveDoctor(Doctor d, User u) 
	{
		String newID = generateNewDocid();
		d.setDoc_id(newID);
		u.setCreatedAt();
		u.setRole(Role.DOCTOR);
		User saveUser = ur.saveUser(u);
		d.setStatus(Status.ACTIVE);
		d.setUser_id(saveUser);
		Doctor save = dr.save(d);
		return save;
	}

	public Optional<Doctor> getDoctorById(String id) 
	{
		return dr.findById(id);
	}

	public List<Doctor> getAllDoctor() {
		List<Doctor> all = dr.findAll();
		return all;
	}

	public void DeleteDoctorById(String id) 
	{
		dr.deleteById(id);
	}

	public Doctor getByEmail(String email) 
	{
		return dr.findByEmail(email);
	}

	public List<Doctor> getBySpecialization(String specialization) 
	{
		return dr.findBySpecialization(specialization);
	}

	public List<Doctor> getTop5Doctors()
	{
		return dr.findTop5ByOrderByExpreinceDesc();
	}

	public List<Doctor> getByNameAndSpecialization(String name, String specialization) {
		List<Doctor> doctors = dr.findByNameLike(name);
		return doctors.stream()
				.filter(doc -> doc.getSpecializations().stream()
						.anyMatch(special -> special.getId().equalsIgnoreCase(specialization)))
				.collect(Collectors.toList());
	}

	public List<Status> getAllDoctorStatus() 
	{
		return Arrays.asList(Status.values());
	}

	public List<Doctor> getByStatus(Status status) 
	{
		return dr.findDoctorsByStatus(status);
	}
}

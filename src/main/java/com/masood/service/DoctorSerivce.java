package com.masood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Doctor;
import com.masood.repository.DoctorRepoInterface;

import jakarta.transaction.Transactional;

@Service("Doctor_service")
@Transactional
public class DoctorSerivce implements DoctorServiceInterface {


	@Autowired
	private DoctorRepoInterface dr;

   

	public Doctor saveDoctor(Doctor d) {
		String lastEntry = dr.findLastEntry();
		String id = "";
		if (lastEntry != null) {
			String last3Digit = lastEntry.substring(2);
			Integer lastNumber = Integer.parseInt(last3Digit);
			lastNumber++;
			id = String.format("DOC%03d", lastNumber);
		} else {
			id = "DOC001";
		}
		d.setDoc_id(id);
		Doctor save = dr.save(d);
		return save;
	}

	public Optional<Doctor> getDoctorById(String id) {
		return dr.findById(id);
	}

	public List<Doctor> getAllDoctor() {
		List<Doctor> all = dr.findAll();
		return all;
	}

	public void DeleteDoctorById(String id) {
		dr.deleteById(id);
	}

	public Doctor getByEmail(String email)
	{
		return dr.findByEmail(email);
	}

	public List<Doctor> getBySpecialization(String specialization) {
		return dr.findBySpecialization(specialization);
	}

	public List<Doctor> getTop5Doctors() {
		return dr.findTop5ByOrderByYearsOfExperienceDesc();
	}

	public List<Doctor> getByNameAndSpecialization(String name, String specialization) {
		List<Doctor> doctors = dr.findByNameLike(name);
		return doctors.stream().filter(doc -> doc.getSpecialized_at().stream()
				.anyMatch(special -> special.getId().equalsIgnoreCase(specialization)))
				.collect(Collectors.toList());
	}

}

package com.masood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.Doctor;
@Repository("DoctorRepo")
public interface DoctorRepoInterface extends JpaRepository<Doctor, String> 
{
	public Doctor getDoctorByEmail(String email);
	public List<Doctor> getDoctorBySpecialization(String specialization);
	
}

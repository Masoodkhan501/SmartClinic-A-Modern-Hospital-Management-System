package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.Patient;
@Repository("patientRepo")
public interface patientRepo extends JpaRepository<Patient, String> 
{

}

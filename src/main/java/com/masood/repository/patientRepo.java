package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.Patient;

public interface patientRepo extends JpaRepository<Patient, String> 
{

}

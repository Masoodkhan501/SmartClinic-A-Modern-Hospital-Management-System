package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.PatientBills;
@Repository("patientBillsRepo")
public interface PatientBillsRepo extends JpaRepository<PatientBills, Long>
{

}

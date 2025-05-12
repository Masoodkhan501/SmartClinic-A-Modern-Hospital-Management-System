package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.PatientBills;

public interface PatientBillsRepo extends JpaRepository<PatientBills, Long>
{

}

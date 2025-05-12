package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.DoctorBills;

public interface DoctorpaymentRepo extends JpaRepository<DoctorBills, Long> 
{

}

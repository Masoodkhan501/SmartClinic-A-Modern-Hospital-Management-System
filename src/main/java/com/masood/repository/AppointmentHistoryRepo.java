package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.AppointmentHIstory;

public interface AppointmentHistoryRepo extends JpaRepository<AppointmentHIstory, Long> 
{

}

package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> 
{

}

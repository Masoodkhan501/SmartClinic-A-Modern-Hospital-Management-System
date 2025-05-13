package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.Appointment;
@Repository("AppointmentRepo")
public interface AppointmentRepo extends JpaRepository<Appointment, Long> 
{

}

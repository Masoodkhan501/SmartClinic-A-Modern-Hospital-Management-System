package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.AppointmentHistory;
@Repository("AppointmentHistoryRepo")
public interface AppointmentHistoryRepo extends JpaRepository<AppointmentHistory, Long> 
{
	
}

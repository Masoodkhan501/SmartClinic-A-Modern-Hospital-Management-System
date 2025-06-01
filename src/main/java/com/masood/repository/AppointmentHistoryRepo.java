package com.masood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.AppointmentHistory;
@Repository("AppointmentHistoryRepo")
public interface AppointmentHistoryRepo extends JpaRepository<AppointmentHistory, Long> 
{
	public List<AppointmentHistory> findAllByOrderByIdDesc();
}

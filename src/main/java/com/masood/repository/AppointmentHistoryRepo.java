package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.AppointmentHIstory;
@Repository("AppointmentHistoryRepo")
public interface AppointmentHistoryRepo extends JpaRepository<AppointmentHIstory, Long> 
{

}

package com.masood.service;

import java.util.List;

import com.masood.model.AppointmentHistory;

public interface AppointmentHistoryInterface 
{
	public AppointmentHistory save(AppointmentHistory history);

    public List<AppointmentHistory> findAll();

    public List<AppointmentHistory> findByAppointmentId(Long appointmentId);
    
    public void deleteAppointmentById(Long id);
}

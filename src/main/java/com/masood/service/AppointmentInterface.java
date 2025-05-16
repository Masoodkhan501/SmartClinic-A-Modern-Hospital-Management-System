package com.masood.service;

import java.util.List;

import com.masood.model.Appointment;

public interface AppointmentInterface 
{
	public Appointment saveAppointment(Appointment a);
	public Appointment getAppointmentbyId(Long id);
	public List<Appointment> getAllAppointment();
	public void deleteAppointmentById(Long id);
}

package com.masood.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.masood.model.Appointment;

public interface AppointmentInterface 
{
	public Appointment saveAppointment(Appointment a);
	public Optional<Appointment> getAppointmentbyId(Long id);
	public List<Appointment> getAllAppointment();
	public void deleteAppointmentById(Long id);
	public List<Appointment> getAppointmentByStatus(String Status);
	public List<Appointment> getAppointmentByDisease(String disease);
	public List<Appointment> getByDate(LocalDate date);
	public List<Appointment> getByPatient(String id);
	public List<Appointment> getByDoctor(String id);
}

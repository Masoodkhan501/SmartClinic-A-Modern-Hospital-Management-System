package com.masood.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Appointment;
import com.masood.repository.AppointmentRepo;
@Service("Appointmentservice")
public class AppointmentService implements AppointmentInterface
{
	@Autowired
	private AppointmentRepo ar;

	public Appointment saveAppointment(Appointment a)
	{
		return ar.save(a);
		
	}

	public Optional<Appointment> getAppointmentbyId(Long id) 
	{
		return ar.findById(id);
	}

	public List<Appointment> getAllAppointment() 
	{
		return ar.findAll();
	}

	public void deleteAppointmentById(Long id) 
	{
		ar.deleteById(id);
	}

	public List<Appointment> getAppointmentByStatus(String Status)
	{
		return ;
	}

	@Override
	public List<Appointment> getAppointmentByDisease(String disease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getByPatient(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getByDoctor(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getByDoctorName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getByPatientName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

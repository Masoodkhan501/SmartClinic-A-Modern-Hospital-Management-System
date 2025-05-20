package com.masood.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Appointment;
import com.masood.model.AppointmentHIstory;
import com.masood.model.Appointmentstatus;
import com.masood.repository.AppointmentHistoryRepo;
import com.masood.repository.AppointmentRepo;

import jakarta.transaction.Transactional;
@Service("Appointmentservice")
@Transactional
public class AppointmentService implements AppointmentInterface
{
	@Autowired
	private AppointmentRepo ar;
	@Autowired
	private AppointmentHistoryRepo ahr;

	public Appointment saveAppointment(Appointment a,AppointmentHIstory ah)
	{
		Appointment save = ar.save(a);
		ah.setAppoint_id(a);
		ahr.save(ah);
		return save;
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

	public List<Appointment> getAppointmentByStatus(Appointmentstatus Status)
	{
		return ar.findAppointmentByStatus(Status);
	}

	public List<Appointment> getAppointmentByDisease(String disease) 
	{
		return ar.findAppointmentByDisease(disease);
	}

	public List<Appointment> getByDate(Date date)
	{
		return ar.findByDateofAppointment(date);
	}

	public List<Appointment> getByPatient(String id)
	{
		return ar.findByPatient(id);
	}

	public List<Appointment> getByDoctor(String id)
	{
		return ar.findByDoctor(id);
	}

	public List<Appointment> getByDoctorName(String name) {
		return ar.findByDoctorName(name);
	}

	public List<Appointment> getByPatientName(String name) {
		return ar.findByPatientName(name);
	}

	public List<Appointmentstatus> getAppointmentStatus() {
		 return Arrays.asList(Appointmentstatus.values());
	}

	

}

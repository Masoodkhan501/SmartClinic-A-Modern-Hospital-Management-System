package com.masood.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.masood.model.Appointment;
import com.masood.model.AppointmentHIstory;
import com.masood.model.Appointmentstatus;
import com.masood.model.Doctor;
import com.masood.model.Patient;
import com.masood.repository.AppointmentHistoryRepo;
import com.masood.repository.AppointmentRepo;

import jakarta.transaction.Transactional;

@Service("Appointmentservice")
@Transactional
public class AppointmentService implements AppointmentInterface {
	@Autowired
	private AppointmentRepo ar;
	@Autowired
	private AppointmentHistoryRepo ahr;

	public Appointment saveAppointment(Appointment a, AppointmentHIstory ah) {
		a.setdateofAppointment();
		Appointment save = ar.save(a);
		ah.setAppoint_id(a);
		ah.setDate_changed();
		ahr.save(ah);
		return save;
	}

	public Optional<Appointment> getAppointmentbyId(Long id) {
		return ar.findById(id);
	}

	public List<Appointment> getAllAppointment() {
		return ar.findAll();
	}

	public void deleteAppointmentById(Long id) {
		ar.deleteById(id);
	}

	public List<Appointment> getAppointmentByStatus(Appointmentstatus Status) {
		return ar.findAppointmentByStatus(Status);
	}

	public List<Appointment> getAppointmentByDisease(String disease) {
		return ar.findAppointmentByDisease(disease);
	}

	public List<Appointment> getByDate(Date date) {
		return ar.findByDateofAppointment(date);
	}

	public List<Appointment> getByPatient(String id) {
		return ar.findByPatient(id);
	}

	public List<Appointment> getByDoctor(String id) {
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

	public Appointment getLatestAppointmentWhoseappointmentisComplete() {
		List<Appointment> appointments = ar.findLatestCompletedAppointments(Appointmentstatus.DONE, PageRequest.of(0, 1));
	    return appointments.isEmpty() ? null : appointments.get(0);
	}

	public List<Appointment> getByDateBetween(Date d1, Date d2) {
		return ar.findByDateofAppointmentBetween(d1, d2);
	}

	public List<Appointment> getByDateAfter(Date d) {
		return ar.findByDateofAppointmentAfter(d);
	}
	
	public Appointment getLatestOperationAppointment() {
	    List<Appointment> ops = ar.findLatestOperationDate(Appointmentstatus.DONE, PageRequest.of(0, 1));
	    return ops.isEmpty() ? null : ops.get(0);
	}
	
	public Appointment getLatestOperationAppointmentByDoctor(Long docId) {
	    List<Appointment> ops = ar.findLatestOperationDateByDoctor(docId, Appointmentstatus.DONE, PageRequest.of(0, 1));
	    return ops.isEmpty() ? null : ops.get(0);
	}

	public List<Doctor> getDoctorByPatientName(String name) {
		return ar.findDoctorByPatientName(name);
	}

	public List<Patient> getPatientByDoctorName(String name) {
		return ar.findPatientsByDoctorName(name);
	}

	public List<Doctor> getDoctorByPatientId(String id) 
	{
		return ar.findDoctorByPatientId(id);
	}

	public List<Patient> getPatientByDoctorId(String id) 
	{
		return ar.findPatientByDoctorId(id);
	}
}

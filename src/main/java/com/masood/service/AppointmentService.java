package com.masood.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.masood.model.Appointment;
import com.masood.model.Appointmentstatus;
import com.masood.model.Doctor;
import com.masood.model.Patient;
import com.masood.model.PaymentStatus;
import com.masood.repository.AppointmentRepo;

import jakarta.transaction.Transactional;

@Service("Appointmentservice")
@Transactional
public class AppointmentService implements AppointmentInterface {
	@Autowired
	private AppointmentRepo ar;

	public Appointment saveAppointment(Appointment a) {
		a.setStatus(Appointmentstatus.PENDING);
		a.setPaymentStatus(PaymentStatus.UNPAID);
		 return ar.save(a); 
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

	public List<Appointment> getByDate(Date date) {
		return ar.findByDateofAppointment(date);
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

	public List<Doctor> getDoctorByPatientId(String id) 
	{
		return ar.findDoctorByPatientId(id).stream().distinct().collect(Collectors.toList());
	}

	public List<Patient> getPatientByDoctorId(String id) 
	{
		return ar.findPatientByDoctorId(id).stream().distinct().collect(Collectors.toList());
	}

	public List<Appointment> getAppointmentsWhereOperationRequiredIsYes() {
		return ar.findByOperationRequiredYes();
	}

	public List<Appointment> getAppointmentsByPaymentStatus(PaymentStatus paymentStatus) {
		return ar.findByPaymentStatus(paymentStatus);
	}

	public List<Appointment> getUnpaidAppointmentsByPatient(String id) {
		return getByPatient(id).stream()
				.filter(p->Objects.equals(p.getPaymentStatus(), PaymentStatus.UNPAID) )
				.collect(Collectors.toList());
	}

	public List<Appointment> getallAppointmentInrev() {
		return ar.findAllAppointmentsSortedByAppIdDesc();
	}

	public List<Appointment> getByPatient(String id) {
		return ar.findByPatientOrderByIdDesc(id);
	}

	public List<Appointment> getByDoctor(String id) {
		return ar.findByDoctorOrderByIdDesc(id);
	}
}

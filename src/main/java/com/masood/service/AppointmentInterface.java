package com.masood.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.masood.model.Appointment;
import com.masood.model.Appointmentstatus;
import com.masood.model.Doctor;
import com.masood.model.Patient;
import com.masood.model.PaymentStatus;

public interface AppointmentInterface 
{
	public Appointment saveAppointment(Appointment a);
	public Optional<Appointment> getAppointmentbyId(Long id);
	public List<Appointment> getAllAppointment();
	public void deleteAppointmentById(Long id);
	public List<Appointment> getAppointmentByStatus(Appointmentstatus Status);
	public List<Appointment> getByDate(Date date);
	public List<Appointment> getByPatient(String id);
	public List<Appointment> getByDoctor(String id);
	public List<Appointmentstatus> getAppointmentStatus();
	public Appointment getLatestAppointmentWhoseappointmentisComplete();
	public List<Appointment> getByDateBetween(Date d1,Date d2);
	public List<Appointment> getByDateAfter(Date d);
	public List<Doctor> getDoctorByPatientId(String id);
	public List<Patient> getPatientByDoctorId(String id);
	public List<Appointment> getAppointmentsWhereOperationRequiredIsYes();
	public List<Appointment> getUnpaidAppointmentsByPatient(String id);
    public List<Appointment> getAppointmentsByPaymentStatus(PaymentStatus paymentStatus);
    public List<Appointment> getallAppointmentInrev();
    public Appointment getLatestAppointmentByPatient(String id);
}

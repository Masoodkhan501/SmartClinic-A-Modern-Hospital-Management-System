package com.masood.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Appointment;
import com.masood.model.Appointmentstatus;
import com.masood.model.Doctor;
import com.masood.model.Patient;
import com.masood.model.PaymentStatus;
public interface AppointmentRepo extends JpaRepository<Appointment, Long> 
{
	public List<Appointment> findAppointmentByStatus(Appointmentstatus status);
	
	public List<Appointment> findByDateofAppointment(Date date);
	
	@Query("SELECT a FROM appointment a WHERE a.d_id.doc_id = :id ORDER BY a.app_id DESC")
	public List<Appointment> findByDoctorOrderByIdDesc(@Param("id") String id);
	
	@Query("select a from appointment a where a.p_id.patient_Id=:id ORDER BY a.app_id DESC")
	public List<Appointment> findByPatientOrderByIdDesc(@Param("id") String id);
	
	@Query("SELECT a FROM appointment a ORDER BY a.app_id DESC")
	public List<Appointment> findLatestAppointment(Pageable pageable);
	
	@Query("SELECT a FROM appointment a WHERE a.status = :status ORDER BY a.app_id DESC")
	public List<Appointment> findLatestCompletedAppointments(@Param("status") Appointmentstatus status, PageRequest pageable);
	
	public List<Appointment> findByDateofAppointmentAfter(Date date);
	
	public List<Appointment> findByDateofAppointmentBetween(Date startDate, Date endDate);
	
	@Query("SELECT a FROM appointment a WHERE a.status = :status AND a.dateOfOperation IS NOT NULL ORDER BY a.dateOfOperation DESC")
	public List<Appointment> findLatestOperationDate(@Param("status") Appointmentstatus status, Pageable pageable);
	
	@Query("SELECT a FROM appointment a WHERE a.d_id.id = :docId AND a.status = :status AND a.dateOfOperation IS NOT NULL ORDER BY a.dateOfOperation DESC")
	public List<Appointment> findLatestOperationDateByDoctor(@Param("docId") Long docId, @Param("status") Appointmentstatus status, Pageable pageable);
	
	@Query("select a.p_id from appointment a where a.d_id.doc_id = :id ORDER BY a.p_id DESC")
	public List<Patient> findPatientByDoctorId(@Param("id") String id);
	
	@Query("select a.d_id from appointment a where a.p_id.patient_Id = :id ORDER BY a.d_id DESC")
	public List<Doctor> findDoctorByPatientId(@Param("id") String id);
	
	@Query("SELECT a FROM appointment a WHERE a.operationRequired = com.masood.model.OperationNeeded.YES")
	public List<Appointment> findByOperationRequiredYes();

	public List<Appointment> findByPaymentStatus(PaymentStatus paymentStatus);
	
	@Query("SELECT a FROM appointment a ORDER BY a.app_id DESC")
	public List<Appointment> findAllAppointmentsSortedByAppIdDesc();
}

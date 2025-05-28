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
public interface AppointmentRepo extends JpaRepository<Appointment, Long> 
{
	public List<Appointment> findAppointmentByStatus(Appointmentstatus status);
	@Query("select a from appointment a where LOWER (a.disease.name) LIKE LOWER (CONCAT('%',:disease,'%'))")
	public List<Appointment> findAppointmentByDisease(@Param("disease") String disease);
	public List<Appointment> findByDateofAppointment(Date date);
	@Query("select a from appointment a where a.d_id=:id")
	public List<Appointment> findByDoctor(@Param("id") String id);
	@Query("select a from appointment a where a.p_id=:id")
	public List<Appointment> findByPatient(@Param("id") String id);
	@Query("select a from appointment a where LOWER(a.d_id.user_id.name) LIKE(CONCAT('%',:name,'%'))")
	public List<Appointment> findByDoctorName(@Param("name") String name);
	@Query("select a from appointment a where LOWER(a.p_id.user_id.name) LIKE (CONCAT('%',:name,'%'))")
	public List<Appointment> findByPatientName(@Param("name") String name);
	@Query("SELECT a FROM appointment a ORDER BY a.app_id DESC")
	List<Appointment> findLatestAppointment(Pageable pageable);
	@Query("SELECT a FROM appointment a WHERE a.status = :status ORDER BY a.app_id DESC")
	public List<Appointment> findLatestCompletedAppointments(@Param("status") Appointmentstatus status, PageRequest pageable);
	public List<Appointment> findByDateofAppointmentAfter(Date date);
	public List<Appointment> findByDateofAppointmentBetween(Date startDate, Date endDate);
	@Query("SELECT a FROM appointment a WHERE a.status = :status AND a.dateOfOperation IS NOT NULL ORDER BY a.dateOfOperation DESC")
	public List<Appointment> findLatestOperationDate(@Param("status") Appointmentstatus status, Pageable pageable);
	@Query("SELECT a FROM appointment a WHERE a.d_id.id = :docId AND a.status = :status AND a.dateOfOperation IS NOT NULL ORDER BY a.dateOfOperation DESC")
	public List<Appointment> findLatestOperationDateByDoctor(@Param("docId") Long docId, @Param("status") Appointmentstatus status, Pageable pageable);
	@Query("SELECT DISTINCT a.patient FROM appointment a WHERE a.d_id.user_id.name = :name")
    public List<Patient> findPatientsByDoctorName(@Param("name") String name);
	@Query("SELECT DISTINCT a.patient FROM appointment a WHERE a.p_id.user_id.name = :name")
    public List<Doctor> findDoctorByPatientName(@Param("name") String name);
}

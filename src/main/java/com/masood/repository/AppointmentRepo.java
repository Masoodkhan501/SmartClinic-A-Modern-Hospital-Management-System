package com.masood.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Appointment;
public interface AppointmentRepo extends JpaRepository<Appointment, Long> 
{
	public List<Appointment> findAppointmentByStatus(String status);
	@Query("select a from appointment a where LOWER (a.disease.name) LIKE LOWER (CONCAT('%',':disease','%'))")
	public List<Appointment> findAppointmentByDisease(@Param("disease") String disease);
	public List<Appointment> findByDate(LocalDate date);
//	@Query("select a from appointment a where a.d_id=:id")
	public List<Appointment> findByDoctor(String id);
	public List<Appointment> findByPatient(String id);
	@Query("select a from appointment a where LOWER(a.d_id.user_id.name) LIKE(CONCAT('%',':name','%'))")
	public List<Appointment> findByDoctorName(@Param("name") String name);
	@Query("select a from appointment a where LOWER(a.p_id.user_id.name) LIKE (CONCAT('%',':name','%'))")
	public List<Appointment> findByPatientName(@Param("name") String name);
	
}

package com.masood.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Appointment;
import com.masood.model.Appointmentstatus;
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
	
}

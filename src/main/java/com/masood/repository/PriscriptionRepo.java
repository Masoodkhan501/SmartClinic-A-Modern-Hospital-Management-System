package com.masood.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.priscription;
public interface PriscriptionRepo extends JpaRepository<priscription, Long> 
{
	@Query("select p from priscription p where p.appoint_id.d_id=:id")
	public List<priscription> findByDoctorId(@Param("id") String id);
	@Query("select p from priscription p where p.appoint_id.p_id=:id")
	public List<priscription> findByPatientId(String id);
	@Query("select p from priscription p where LOWER( p.appoint_id.d_id.user_id.name) LIKE (CONCAT('%',:name,'%'))")
	public List<priscription> findByDoctorName(@Param("name") String name);
	@Query("select p from priscription p where LOWER( p.appoint_id.p_id.user_id.name) LIKE (CONCAT('%',:name,'%'))")
	public List<priscription> findByPatientName(@Param("name") String name);
	public Optional<priscription> findByAppointmentId(Long id);
}

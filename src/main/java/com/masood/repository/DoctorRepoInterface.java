package com.masood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Doctor;
import com.masood.model.Status;
public interface DoctorRepoInterface extends JpaRepository<Doctor, String> 
{
	@Query("select d from Doctor d where d.user_id.email = :email")
	public Doctor findByEmail(@Param("email") String email);
	@Query("SELECT d FROM Doctor d JOIN d.specializations s WHERE LOWER(s.specialization) = LOWER(CONCAT('%',:specialization,'%'))")
	public List<Doctor> findBySpecialization(@Param("specialization") String specialization);
	public List<Doctor> findTop5ByOrderByYearsOfExperienceDesc();
	@Query("select d from Doctor d where LOWER(d.user_id.name) LIKE LOWER(CONCAT( '%' ,:name,'%'))")
	public List<Doctor>	findByNameLike(@Param("name") String name);
	@Query("SELECT MAX(d.doc_id) FROM Doctor d")
	public String findLastEntry();
	public List<Doctor> findDoctorsByStatus(Status status);
}

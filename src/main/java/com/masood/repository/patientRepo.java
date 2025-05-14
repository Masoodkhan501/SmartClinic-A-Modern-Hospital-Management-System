package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Patient;
import com.masood.model.User;
public interface patientRepo extends JpaRepository<Patient, String> 
{
	@Query("select p from patient p where p.user_id.email = :email")
	public User findByEmail(@Param("email") String email);
	@Query("select p from patient p where LOWER(p.user_id.name) LIKE LOWER(CONCAT('%',:name,'%'))")
	public User findByNameLike(@Param("name")String name);
}

package com.masood.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Specialized;
public interface SpecilizedRepo extends JpaRepository<Specialized, String> 
{
	public Optional<Specialized> findBySpecialization(String specialization);
	@Query("select s from specialization s JOIN s.specilist d WHERE d.doc_id = :id")
	public List<Specialized> findByDoctorId(@Param("id") String id);
	@Query("select s from specialization s JOIN s.specilist d WHERE LOWER(d.user_id.name) LIKE LOWER(CONCAT('%',:name,'%'))")
	public List<Specialized> findByDoctorName(String name);
	public void deleteSpecializationByName(String name);
}

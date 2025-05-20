package com.masood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Disease;

public interface DiseaseRepo extends JpaRepository<Disease, Long>
{
	public Disease findDiseaseByName(String name);
	public void deleteByName(String name);
	@Query("select d from Disease d where LOWER(d.description) LIKE (CONCAT('%',:description,'%'))")
	public List<Disease> findBydescription(@Param("description") String description);
}

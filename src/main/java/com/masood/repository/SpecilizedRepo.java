package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.Specialized;
@Repository("SpecilizedRepo")
public interface SpecilizedRepo extends JpaRepository<Specialized, String> 
{

}

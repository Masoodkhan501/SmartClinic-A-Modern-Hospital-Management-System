package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.priscription;
@Repository("PriscriptionRepo")
public interface PriscriptionRepo extends JpaRepository<priscription, Long> 
{

}

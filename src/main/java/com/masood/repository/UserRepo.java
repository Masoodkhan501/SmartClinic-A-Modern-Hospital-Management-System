package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masood.model.User;
@Repository("UserRepo")
public interface UserRepo extends JpaRepository<User, Long> 
{

}

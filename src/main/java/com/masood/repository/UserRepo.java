package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.User;
public interface UserRepo extends JpaRepository<User, Long> 
{

}

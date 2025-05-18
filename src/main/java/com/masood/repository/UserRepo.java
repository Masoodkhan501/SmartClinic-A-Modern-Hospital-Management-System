package com.masood.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.Role;
import com.masood.model.User;
public interface UserRepo extends JpaRepository<User, Long> 
{
	public Optional<User> findByRole(Role role);
}

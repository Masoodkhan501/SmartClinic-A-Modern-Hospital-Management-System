package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.Role;
import com.masood.model.User;

public interface UserSeviceInterface 
{
	public User saveUser(User user);
	public Optional<User> findUserById(Long id);
	public List<User> getAllUser();
	public void deleteUserById(Long id);
	public Optional<User> getByRole(Role role);
	public Optional<User> getByName(String name);
	public Optional<User> getByEmail(String email);
	public int isValidPatient(String email, String password);
}

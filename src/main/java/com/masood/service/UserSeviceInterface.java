package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.User;

public interface UserSeviceInterface 
{
	public User saveUser(User user);
	public Optional<User> findUserById(User user);
	public List<User> getAllUser();
	public void deleteUserById(User user);
}

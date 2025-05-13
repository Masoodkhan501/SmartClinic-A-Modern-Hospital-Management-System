package com.masood.service;

import java.util.List;

import com.masood.model.User;

public interface UserSeviceInterface 
{
	public User saveUser(User user);
	public User findUserById(User user);
	public List<User> getAllUser();
	public int deleteUserById(User user);
}

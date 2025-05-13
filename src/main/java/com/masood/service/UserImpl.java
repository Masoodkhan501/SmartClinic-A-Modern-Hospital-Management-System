package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.User;
import com.masood.repository.UserRepo;

@Service("userImplService")
public class UserImpl implements UserSeviceInterface {

	@Autowired
	private UserRepo ur;

	public User saveUser(User user) {
		User save = ur.save(user);
		System.out.println("This message is for the console that user is created successfully");
		return save;
	}

	public User findUserById(User user) {
		Optional<User> gotUser = ur.findById(user.getId());
		User foundUser = null;
		try
		{
			foundUser = gotUser.orElseThrow(()->new RuntimeException("User Not found"));
		}
		catch(RuntimeException re)
		{
			System.out.println(re.getMessage());
		}
		return foundUser;
	}

	public List<User> getAllUser() {
		List<User> all = ur.findAll();
		if(all.isEmpty())
		{
			return null;
		}
		return all;
	}

	public int deleteUserById(User user) {
		User userById = findUserById(user);
		if(userById!=null)
		{
			ur.deleteById(user.getId());
			return 0;
		}
		else
		{
			return 1;
		}
		
	}

}

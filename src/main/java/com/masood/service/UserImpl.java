package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.User;
import com.masood.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service("userImplService")
@Transactional
public class UserImpl implements UserSeviceInterface {

	@Autowired
	private UserRepo ur;

	public User saveUser(User user) {
		User save = ur.save(user);
		System.out.println("This message is for the console that user is created successfully");
		return save;
	}

	public Optional<User> findUserById(Long id) {
		return ur.findById(id);
	}

	public List<User> getAllUser() {
		return ur.findAll();
	}

	public void deleteUserById(Long id) {
		ur.deleteById(id);
	}

}

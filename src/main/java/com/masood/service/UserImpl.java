package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Role;
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

	public Optional<User> getByRole(Role role) {
		return ur.findByRole(role);
	}

	public Optional<User> getByName(String name) {
		return ur.findByName(name);
	}

	public Optional<User> getByEmail(String email) {
		return ur.findByEmail(email);
	}

	public int isValidPatient(String email, String password) 
	{
		Optional<User> user = ur.findByEmail(email);
		if(user.isPresent())
		{
			User u1 = user.get();
			if(u1.getPassword().equals(password))
			{
				if(u1.getRole().equals(Role.PATIENT))
					return 2;
				return 0;
			}
			else
				return 1;
		}
		return 3;
	}

}

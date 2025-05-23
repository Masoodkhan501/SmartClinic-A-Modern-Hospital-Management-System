package com.masood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.masood.model.User;
import com.masood.service.UserImpl;
@Controller("userController")
public class UserController 
{
	@Autowired
	private UserImpl us;
	
	@GetMapping("/new/user")
	public String newUser(Model m)
	{
		User u = new User();
		m.addAttribute("User", u);
		return "createnewUser";
	}
}

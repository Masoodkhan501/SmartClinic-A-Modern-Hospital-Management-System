package com.masood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.masood.model.Role;
import com.masood.model.User;
import com.masood.service.UserImpl;


@Controller("homeContorller")
public class HomeController
{
	@Autowired
	private UserImpl us;
	
	@GetMapping("/")
	public String checkForAdmin()
	{
		String firstpage="";
		Optional<User> byRole = us.getByRole(Role.ADMIN);
		if(byRole.isEmpty())
			firstpage="redirect:admin_login";
		else
			firstpage="redirect:home";
		return firstpage;
	}
	
	@GetMapping("/admin/login")
	public String AdminLogin(Model model)
	{
		User u = new User();
		model.addAttribute("user", u);
		return "save_admin";
	}
	
	public String saveAdmin(@ModelAttribute("user") User u ,Model model)
	{
		User saved = us.saveUser(u);
		model.addAttribute("user", saved);
		return "redirect:admin_homePage";
	}
}

package com.masood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.masood.model.User;
//import com.masood.service.DoctorSerivce;
import com.masood.service.UserImpl;

@Controller("DoctorController")
public class DoctorController 
{
//	@Autowired
//	private DoctorSerivce ds;
	@Autowired
	private UserImpl us;
	
	@GetMapping("/login/doctor")
	public String LoginDoctor(Model model)
	{
		User u = new User();
		boolean isUser=true;
		boolean ispassword=true;
		String role = "doctor";
		model.addAttribute("isuser", isUser);
		model.addAttribute("ispassword",ispassword);
		model.addAttribute("role",role);
		model.addAttribute("User", u);
		return "UserLogin";
	}
	
	@GetMapping("/check/doctor")
	public String validDoctor(@ModelAttribute("User") User u,Model m)
	{
		boolean isUser=true;
		boolean ispassword=true;
		String page = "";
		int validPatient = us.isValidPatient(u.getEmail(), u.getPassword());
		if(validPatient == 3 || validPatient == 0)
		{
			isUser=false;
			ispassword=false;
			page="UserLogin";	
		}
		else if(validPatient == 2)
		{
			isUser=true;
			ispassword=true;
			page="DoctorLandingPage";
		}
		else
		{
			isUser=true;
			ispassword=false;
			page="UserLogin";
		}
		m.addAttribute("isuser", isUser);
		m.addAttribute("ispassword",ispassword);
		return page;
	}
}

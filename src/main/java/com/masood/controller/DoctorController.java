package com.masood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.masood.DTO.DoctorDTO;
import com.masood.model.Doctor;
import com.masood.model.Specialized;
import com.masood.model.User;
import com.masood.service.DoctorSerivce;
import com.masood.service.SpecializedServiceImpl;
import com.masood.service.UserImpl;

@Controller("DoctorController")
public class DoctorController 
{
	@Autowired
	private DoctorSerivce ds;
	@Autowired
	private UserImpl us;
	
	@Autowired
	private SpecializedServiceImpl seps;
	
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
	
	@GetMapping("/new/doctor")
	public String newDoctor(Model m)
	{
		List<Specialized> allSpecialization = seps.getAllSpecialization();
		Doctor d = new Doctor();
		User u = new User();
		DoctorDTO ddto = new DoctorDTO(d, u);
		boolean isrepasswcorrect = true;
		m.addAttribute("DoctorDTO", ddto);
		m.addAttribute("isrepassword", isrepasswcorrect);
		m.addAttribute("specializations", allSpecialization);
		return "createnewDoctor";
	}
	
	@PostMapping("/save/doctor")
	public String saveDoctor(@ModelAttribute("DoctorDTO") DoctorDTO ddto,
			@RequestParam("repassword") String confirmPassword
			,Model m)
	{
		String page="";
		if (confirmPassword.equals(ddto.getUser().getPassword())) {
			ds.saveDoctor(ddto.getDoctor(), ddto.getUser());
			page = "DoctorLandingPage";
		} else {
			boolean isrepasscorrect = false;
			m.addAttribute("isrepassword", isrepasscorrect);
			page = "createnewDoctor";
		}
		return page;
	}
}

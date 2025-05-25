package com.masood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.masood.DTO.PatientDTO;
import com.masood.model.Patient;
import com.masood.model.Role;
import com.masood.model.User;
import com.masood.service.PatientServiceimpl;
import com.masood.service.UserImpl;

@Controller("PatientController")
public class PatientController 
{
	@Autowired
	private UserImpl us;
	@Autowired
	private PatientServiceimpl ps;

	@GetMapping("/new/patient")
	public String newUser(Model m) {
		PatientDTO pdto = new PatientDTO();
		User u = new User();
		Patient p = new Patient();
		pdto.setUser(u);
		pdto.setPatient(p);
		boolean isrepasswcorrect = true;
		m.addAttribute("isrepassword", isrepasswcorrect);
		m.addAttribute("PatientDTO", pdto);
		return "createnewUser";
	}

	@PostMapping("/create/patient")
	public String savePatient(@RequestParam("re_password") String repassword,
			@ModelAttribute("PatientDTO") PatientDTO pdto,
			Model m) {
		String page = "";
		if (repassword.equals(pdto.getUser().getPassword())) {
			User u = pdto.getUser();
			u.setRole(Role.PATIENT);

			Patient p = pdto.getPatient();
			ps.savePatient(p, u);
			page = "patientLandingPage";
		} else {
			boolean isrepasscorrect = false;
			m.addAttribute("isrepassword", isrepasscorrect);
			page = "createnewUser";
		}
		return page;
	}
	
	@PostMapping("/check/patient")
	public String loginPatient(@ModelAttribute("User") User u,Model m)
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
			page="patientLandingPage";
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

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

@Controller("userController")
public class UserController {
	@Autowired
	private UserImpl us;

	@Autowired
	private PatientServiceimpl ps;

	@GetMapping("/new/patient")
	public String newUser(Model m) {
		PatientDTO pdto = new PatientDTO();
		User u = new User();
		Patient p = new Patient();
		u.setRole(Role.PATIENT);
		pdto.setUser(u);
		pdto.setPatient(p);
		m.addAttribute("PatientDTO", pdto);
		return "createnewUser";
	}

	@PostMapping("/create/patient")
	public String savePatient(@RequestParam("re_password") String repassword, @ModelAttribute("PatientDTO") PatientDTO pdto,
			Model m) 
	{
		String page="";
		if(repassword.equals(pdto.getUser().getPassword()))
		{
			User u = pdto.getUser();
			Patient p = pdto.getPatient();
			ps.savePatient(p, u);
			page="patientLandingPage";
		}
		else
		{
			boolean isrepasscorrect=false;
			m.addAttribute("isrepassword", isrepasscorrect);
			page="createnewUser";
		}
		return page;
	}
}

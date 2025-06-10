package com.masood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.masood.model.Role;
import com.masood.model.User;
import com.masood.service.UserImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


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
			firstpage="redirect:/admin/create";
		else
			firstpage="redirect:/home";
		return firstpage;
	}
	
	@GetMapping("/admin/create")
	public String AdminLogin(Model model)
	{
		User u = new User();
		boolean ispassword=true;
		model.addAttribute("ispaword", ispassword);
		model.addAttribute("existing","no");
		model.addAttribute("user", u);
		return "admin_login";
	}
	
	@PostMapping("/check/admin")
	public String postMethodName(@ModelAttribute("user") User u,
			@RequestParam(name ="confirmPassword", required=false) String confirmPass,
			Model model,HttpSession session)
	{
		Optional<User> byEmail = us.getByEmail(u.getEmail());
		if(byEmail.isPresent())
		{
			User u1 = byEmail.get();
			if(u.getPassword().equals(u1.getPassword()))
			{
				session.setAttribute("user", u1);
				return "redirect:/admin/page";
			}
			else
			{
				boolean ispassword=false;
				model.addAttribute("ispassword", ispassword);
				model.addAttribute("existing","yes");
				return "admin_login";
			}	
		}
		else
		{
			if(u.getPassword().equals(confirmPass))
			{
				u.setCreatedAt();
				u.setRole(Role.ADMIN);
				us.saveUser(u);
				session.setAttribute("user", u);
				return "redirect:/admin/page";
			}
			else
			{
				boolean ispassword=true;
				model.addAttribute("user", u);
				model.addAttribute("existing","no");
				model.addAttribute("ispassword",ispassword);
				return "admin_login";
			}
		}
		
	}
	
	@GetMapping({"/home","/logout/home"})
	public String Home()
	{
		return "home";
	}

	@GetMapping({"/book/appointment","/login/patient"})
	public String PatientLogin(Model model,HttpSession session,HttpServletRequest req)
	{
		User u = new User();
		boolean isUser=true;
		boolean ispassword=true;
		String role = "patient";
		model.addAttribute("isuser", isUser);
		model.addAttribute("ispassword",ispassword);
		model.addAttribute("role", role);
		model.addAttribute("User", u);
		String url = req.getRequestURI();
		if(url.contains("/book/appointment"))
			session.setAttribute("dest", "appointmentpage");
		else
			session.setAttribute("dest", "patientpage");
		return "UserLogin";
	}
	
	@PostMapping("/check/user")
	public String userLogin(@RequestParam("sender") String role,RedirectAttributes redirectmsg,
			@ModelAttribute("User") User u)
	{
		if(role.equals("patient"))
		{
			redirectmsg.addFlashAttribute("role", role);
			redirectmsg.addFlashAttribute("User", u);
			return "redirect:/check/patient";
		}
		else
		{
			redirectmsg.addFlashAttribute("role", role);
			redirectmsg.addFlashAttribute("User", u);
			return "redirect:/check/doctor";
		}
	}
	
	@GetMapping("/career/doctor")
	public String careerpage()
	{
		return "carewellcareerpage";
	}
}

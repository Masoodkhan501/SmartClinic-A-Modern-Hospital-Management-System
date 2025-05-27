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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


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
			firstpage="redirect:/admin/login";
		else
			firstpage="redirect:/home";
		return firstpage;
	}
	
	@GetMapping("/admin/login")
	public String AdminLogin(Model model)
	{
		User u = new User();
		model.addAttribute("user", u);
		return "admin_login";
	}
	
	@PostMapping("/save/admin")
	public String postMethodName(@ModelAttribute("user") User u,
			@RequestParam("confirmPassword") String confirmPass,
			Model model)
	{
		if(u.getPassword().equals(confirmPass))
		{
			u.setCreatedAt();
			u.setRole(Role.ADMIN);
			us.saveUser(u);
		}
		else
		{
			model.addAttribute("user", u);
			return "admin_login";
		}
		return "redirect:admin_homePage";
	}
	
	@GetMapping("/home")
	public String Home()
	{
		return "Home";
	}

	@GetMapping({"/book/appointment","/login/patient"})
	public String PatientLogin(Model model,HttpServletResponse res)
	{
		User u = new User();
		boolean isUser=true;
		boolean ispassword=true;
		String role = "patient";
		model.addAttribute("isuser", isUser);
		model.addAttribute("ispassword",ispassword);
		model.addAttribute("role", role);
		model.addAttribute("User", u);
		Cookie cookie = new Cookie("role", role);
		cookie.setPath("/");
		res.addCookie(cookie);
		
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

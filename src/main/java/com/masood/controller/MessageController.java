package com.masood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.masood.model.Doctor;
import com.masood.model.Message;
import com.masood.model.Patient;
import com.masood.model.Role;
import com.masood.model.User;
import com.masood.service.DoctorSerivce;
import com.masood.service.MessageService;
import com.masood.service.PatientServiceimpl;
import com.masood.service.UserImpl;

import jakarta.servlet.http.HttpServletRequest;
//import com.masood.service.MessageService;
//import com.masood.service.UserImpl;

@Controller("MessageController")
public class MessageController 
{
	
	@Autowired
	private MessageService ms;
	@Autowired
	private PatientServiceimpl ps;
	@Autowired
	private DoctorSerivce ds;
	@Autowired
	private UserImpl us;
	
	@GetMapping("/doctor/send/msg/{id}")
	public String sendMessage(@SessionAttribute("user") User u,
			@PathVariable String id,
			Model m)
	{
		Message msg = new Message();
		msg.setSender(u);
		Optional<Patient> patientById = ps.getPatientById(id);
		Patient patient = patientById.get();
		msg.setReceiver(patient.getUser_id());
		m.addAttribute("message", msg);
		m.addAttribute("by", "doctor");
		return "messagepage";
	}
	
	@PostMapping("/message/send")
	public String saveMessage(@ModelAttribute("message") Message msg,
			@SessionAttribute("user") User u,
			@RequestParam String by)
	{
		ms.sendMessage(msg);
		String redirect = "";
		if(by.equalsIgnoreCase("doctor"))
			redirect = "redirect:/doctor/patients";
		else if(by.equalsIgnoreCase("patient"))
			redirect = "redirect:/patient/unread/messages";
		else
			redirect = "redirect:/admin/unread/messages";
		return redirect;
	}
	
	@GetMapping("/patient/send/message/{id}")
	public String patientsendMessage(@SessionAttribute("user") User u,
			@PathVariable Long id,
			Model m)
	{
		Message msg = new Message();
		msg.setSender(u);
		Optional<User> userById = us.findUserById(id);
		User user = userById.get();
		Doctor doctor = ds.getByEmail(user.getEmail());
		msg.setReceiver(doctor.getUser_id());
		m.addAttribute("message", msg);
		m.addAttribute("by", "patient");
		return "messagepage";
	}
	
	
	@GetMapping({"/admin/sendmsg/doctor/{id}","/admin/sendmsg/patient/{id}"})
	public String adminsendMessage(@SessionAttribute("user") User u,
			@PathVariable String id, Model m,HttpServletRequest req)
	{
		Message msg = new Message();
		msg.setSender(u);
		String uri = req.getRequestURI();
		if(uri.contains("/sendmsg/doctor"))
		{
			Optional<Doctor> doctorById = ds.getDoctorById(id);
			Doctor doctor = doctorById.get();
			msg.setReceiver(doctor.getUser_id());
		}
		else
		{
			Optional<Patient> patientById = ps.getPatientById(id);
			Patient patient = patientById.get();
			msg.setReceiver(patient.getUser_id());
		}
		m.addAttribute("by", "admin");
		m.addAttribute("message", msg);
		return "messagepage";
	}
	
	@GetMapping("/patient/messages/admin")
	public String patienttoAdmin(@SessionAttribute("user") User u,Model m)
	{
		Optional<User> byRole = us.getByRole(Role.ADMIN);
		User user = byRole.get();
		Message msg= new Message();
		msg.setSender(u);
		msg.setReceiver(user);
		m.addAttribute("message", msg);
		m.addAttribute("by", "patient");
		return "messagepage";
	}
}

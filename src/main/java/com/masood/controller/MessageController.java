package com.masood.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.masood.model.Message;
import com.masood.model.User;
//import com.masood.service.MessageService;
//import com.masood.service.UserImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller("MessageController")
public class MessageController 
{
//	
//	@Autowired
//	private MessageService ms;
//	@Autowired
//	private UserImpl us;
//	
	@GetMapping({"/doctor/messages/new","/patient/messages/new"})
	public String checkSender(HttpServletRequest req,RedirectAttributes redirect)
	{
		String url = req.getRequestURI();
		if(url.contains("/doctor/"))
		{
			redirect.addAttribute("by","doctor");
			return "redirect:/doctor/send/message";
		}
		else if(url.contains("/patient/"))
		{
			redirect.addAttribute("by","patient");
			return "redirect:/patient/send/message";
		}
		else
		{
			return "redirect:/admin/send/message";
		}
	}
	
	@GetMapping("doctor/send/message")
	public String sendMessage(@SessionAttribute("user") User u,
			@RequestParam String by,
			Model m)
	{
		Message msg = new Message();
		msg.setSender(u);
		m.addAttribute("message", msg);
		m.addAttribute("by", by);
		return "messagepage";
	}
	
	@PostMapping("save/message")
	public String saveMessage(@ModelAttribute("message") Message msg,
			@RequestParam("receiver") String reciever,
			@SessionAttribute("user") User u)
	{
		return "";
	}
}

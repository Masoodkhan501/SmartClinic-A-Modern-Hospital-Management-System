package com.masood.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.masood.DTO.PatientDTO;
import com.masood.model.Appointment;
import com.masood.model.Patient;
import com.masood.model.Role;
import com.masood.model.User;
import com.masood.model.priscription;
import com.masood.service.AppointmentService;
import com.masood.service.MessageService;
import com.masood.service.PatientServiceimpl;
import com.masood.service.PriscriptionServiceImpl;
import com.masood.service.UserImpl;

import jakarta.servlet.http.HttpSession;

@Controller("PatientController")
public class PatientController 
{
	@Autowired
	private UserImpl us;
	@Autowired
	private PatientServiceimpl ps;
	@Autowired
	private AppointmentService as;
	@Autowired
	private MessageService ms;
	@Autowired
	private PriscriptionServiceImpl pres;

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

	@GetMapping("/create/patient")
	public String savePatient(@RequestParam("re_password") String repassword,
			@ModelAttribute("PatientDTO") PatientDTO pdto,HttpSession session,
			Model m) {
		String page = "";
		if (repassword.equals(pdto.getUser().getPassword())) {
			User u = pdto.getUser();
			u.setRole(Role.PATIENT);

			Patient p = pdto.getPatient();
			Patient savePatient = ps.savePatient(p, u);
			u = pdto.getUser();
//			Optional<User> userById = us.findUserById(savePatient.getUser_id());
//			session.setAttribute("user", userById.get());
			session.setAttribute("patient",savePatient);
			session.setAttribute("user", u);
			page = "redirect:/patient/page";
		} else {
			boolean isrepasscorrect = false;
			m.addAttribute("isrepassword", isrepasscorrect);
			page = "createnewUser";
		}
		return page;
	}
	
	@GetMapping("/check/patient")
	public String loginPatient(@ModelAttribute("User") User u ,Model m,HttpSession session)
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
			Optional<User> u1 = us.getByEmail(u.getEmail());
			User u2 = u1.get();
			Patient p = ps.getByEmail(u.getEmail());
			session.setAttribute("user",u2);
			session.setAttribute("patient", p);
			page="redirect:/patient/page";
		}
		else
		{
			isUser=true;
			ispassword=false;
			page="UserLogin";
		}
		m.addAttribute("isuser", isUser);
		m.addAttribute("ispassword",ispassword);
		m.addAttribute("role", "patient");
		m.addAttribute("User",u);
		return page;
	}
	
	@GetMapping("/patient/page")
	public String patientpage(@SessionAttribute("user") User u,
			@SessionAttribute("patient") Patient p,Model m)
	{
		Double price = ps.getTotalAmountofDueBills(p);
		Math.round(price);
		Byte patientAge = ps.getPatientAge(p);
		int sizeofAppt = as.getAllAppointment().size();
		Date d = new Date();
		int upcomingappt = as.getByDateAfter(d).size();
		int noofUnread = ms.getMessagesByStatus("unread").size();
		Appointment latestAppointment = as.getLatestOperationAppointment();
		priscription getlatestpriscription = pres.getlatestpriscription();
		PatientDTO pdto = new PatientDTO(p, u);
		pdto.setAppoint(latestAppointment);
		pdto.setPris(getlatestpriscription);
		m.addAttribute("PatientDTO", pdto);
		m.addAttribute("age", patientAge);
		m.addAttribute("lastVisit", latestAppointment);
		m.addAttribute("noofAppointments",sizeofAppt);
		m.addAttribute("upcomingAppointments", upcomingappt);
		m.addAttribute("noofUnreadMessages",noofUnread);
		m.addAttribute("TotalDeu", price);
		boolean isNewPatient = latestAppointment == null && getlatestpriscription == null && sizeofAppt == 0;
		m.addAttribute("isNew", isNewPatient);
		return "patientLandingPage";
	}
}

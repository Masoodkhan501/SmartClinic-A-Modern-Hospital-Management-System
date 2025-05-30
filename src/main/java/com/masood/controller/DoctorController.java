package com.masood.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.masood.DTO.DoctorDTO;
import com.masood.DTO.DoctorDetailedPage;
import com.masood.model.Appointment;
import com.masood.model.Doctor;
import com.masood.model.Message;
import com.masood.model.Patient;
import com.masood.model.Specialized;
import com.masood.model.User;
import com.masood.service.AppointmentService;
import com.masood.service.DoctorSerivce;
import com.masood.service.MessageService;
//import com.masood.service.PatientServiceimpl;
//import com.masood.service.PriscriptionServiceImpl;
import com.masood.service.SpecializedServiceImpl;
import com.masood.service.UserImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller("DoctorController")
public class DoctorController 
{
	@Autowired
	private DoctorSerivce ds;
	@Autowired
	private UserImpl us;
	
	@Autowired
	private SpecializedServiceImpl seps;
	@Autowired
	private AppointmentService as;
	@Autowired
	private MessageService ms;
//	@Autowired
//	private PriscriptionServiceImpl pres;
//	@Autowired
//	private PatientServiceimpl ps;
	
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
	public String validDoctor(@ModelAttribute("User") User u,Model m,HttpSession session)
	{
		boolean isUser=true;
		boolean ispassword=true;
		String page = "";
		int validPatient = us.isValidDoctor(u.getEmail(), u.getPassword());
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
			Optional<User> user = us.getByEmail(u.getEmail());
			User u1 = user.get();
			Doctor d = ds.getByEmail(u.getEmail());
			session.setAttribute("user", u1);
			session.setAttribute("doctor", d);
			page="redirect:/doctor/page";
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
			@RequestParam("repassword") String confirmPassword,HttpSession session
			,Model m)
	{
		String page="";
		if (confirmPassword.equals(ddto.getUser().getPassword())) {
			Doctor saveDoctor = ds.saveDoctor(ddto.getDoctor(), ddto.getUser());
			Optional<Doctor> doc = ds.getDoctorById(saveDoctor.getDoc_id());
			Doctor d = doc.get();
			Optional<User> user = us.getByEmail(saveDoctor.getUser_id().getEmail());
			User u = user.get();
			session.setAttribute("user", u);
			session.setAttribute("doctor", d);
			page = "redirect:/doctor/page";
		} else {
			boolean isrepasscorrect = false;
			m.addAttribute("isrepassword", isrepasscorrect);
			page = "createnewDoctor";
		}
		return page;
	}
	
	@GetMapping("/doctor/page")
	public String doctorpage(@SessionAttribute("user") User u,
			@SessionAttribute("doctor") Doctor d,
			HttpSession session,Model m)
	{
		DoctorDTO ddto = new DoctorDTO(d, u);
		m.addAttribute("DoctorDTO", ddto);
		List<Appointment> appointmentbydate = as.getByDate(new Date());
		m.addAttribute("todayAppointmentsCount",appointmentbydate.size() );
		List<Appointment> upcommingappt = as.getByDateAfter(new Date());
		m.addAttribute("upcomingAppointmentsCount", upcommingappt.size());
		List<Message> messagesByStatus = ms.getMessagesByStatus("unread");
		m.addAttribute("unreadMessagesCount", messagesByStatus.size());
		List<Patient> patientByDoctorName = as.getPatientByDoctorId(d.getDoc_id());
		m.addAttribute("totalPatientsCount",patientByDoctorName.size());
		m.addAttribute("todayAppointments", appointmentbydate);
		session.setAttribute("DoctorDTO", ddto);
		DoctorDetailedPage ddp = new DoctorDetailedPage(appointmentbydate, appointmentbydate, messagesByStatus, patientByDoctorName);
		session.setAttribute("docdetails", ddp);
		boolean isnew = true;
		if(patientByDoctorName.size()<0)
			isnew=false;
		m.addAttribute("isnew",isnew );
		LocalDate dates = LocalDate.now().minusDays(1);
		m.addAttribute("dates",dates);
		return "DoctorLandingPage";
	}
	
	@GetMapping({"/doctor/appointments/today",
		"/doctor/appointments/upcoming",
		"/doctor/unread/messages",
		"/doctor/patients"})
	public String handlingDoctorDetialPage(HttpServletRequest req,Model m,
			@SessionAttribute("docdetails") DoctorDetailedPage ddp)
	{
		String url = req.getRequestURI();
		String reason = "";
		if(url.contains("/appointments/today"))
		{
			reason = "todayappointments";
			m.addAttribute("todayappt",ddp.getTodayAppointments());
		}
		else if(url.contains("/appointments/upcoming"))
		{
			reason = "upcomingappointments";
			m.addAttribute("upcomingappt",ddp.getUpcomingAppointments());
		}
		else if(url.contains("/unread/messages")) 
		{
			reason = "unreadmessages";
			m.addAttribute("unreadmsg", ddp.getUnreadMessages());
		}
		else
		{
			reason = "doctorpatients";
			m.addAttribute("docpatients", ddp.getRelatedPatients());
		}
		m.addAttribute("reason", reason);
		return "doctordetailspage";
	}
}

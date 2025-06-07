package com.masood.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
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
	
	@GetMapping({"/doctor/page","/admin/manage/doctor/{id}"})
	public String doctorpage(@SessionAttribute("user") User u,
			@SessionAttribute(name = "doctor",required = false) Doctor d,
			@PathVariable(required=false) String id,
			HttpSession session,Model m,
			HttpServletRequest req)
	{
		String url = req.getRequestURI();
		Doctor d1=new Doctor();
		User u1=new User();
		boolean isadmin = true;
		if(url.contains("/doctor/page"))
		{
			d1=d;
			u1=u;
			isadmin = false;
		}
		else
		{
			Optional<Doctor> doctorById = ds.getDoctorById(id);
			d1 = doctorById.get();
			Optional<User> byEmail = us.getByEmail(d1.getUser_id().getEmail());
			u1 = byEmail.get();
			isadmin = true;
		}
		DoctorDTO ddto = new DoctorDTO(d1, u1);
		m.addAttribute("DoctorDTO", ddto);
		List<Appointment> appointmentbydate = as.getByDate(new Date());
		m.addAttribute("todayAppointmentsCount",appointmentbydate.size() );
		List<Appointment> upcommingappt = as.getByDateAfter(new Date());
		m.addAttribute("upcomingAppointmentsCount", upcommingappt.size());
		List<Message> messagesByStatus = ms.getMessageByUserandStatus(d1.getUser_id().getId(),"unread");
		m.addAttribute("unreadMessagesCount", messagesByStatus.size());
		List<Patient> patientByDoctorName = as.getPatientByDoctorId(d1.getDoc_id());
		m.addAttribute("totalPatientsCount",patientByDoctorName.size());
		m.addAttribute("todayAppointments", appointmentbydate);
		session.setAttribute("DoctorDTO", ddto);
		List<Message> bySenderId = ms.getBySenderId(d1.getUser_id().getId());
		if(!bySenderId.isEmpty())
		{
			ListIterator<Message> i = bySenderId.listIterator();
			while(i.hasNext())
			{
				Message m1 = i.next();
				System.out.println(m1.getReceiver());
			}
		}
		else
		{
			System.out.println("There are no messages from this doctor");
		}
		List<Message> byRecieverId = ms.getByRecieverId(d1.getUser_id().getId());
		if(!byRecieverId.isEmpty())
		{
			ListIterator<Message> i = byRecieverId.listIterator();
			while(i.hasNext())
			{
				Message m1 = i.next();
				System.out.println(m1.getSender());
			}
		}
		else
		{
			System.out.println("There are no messages from this doctor");
		}
		DoctorDetailedPage ddp = new DoctorDetailedPage(appointmentbydate, upcommingappt, messagesByStatus, patientByDoctorName,bySenderId,byRecieverId);
		session.setAttribute("docdetails", ddp);
		boolean isnew = true;
		if(patientByDoctorName.size()<0)
			isnew=false;
		m.addAttribute("isnew",isnew );
		LocalDate dates = LocalDate.now().minusDays(1);
		m.addAttribute("dates",dates);
		m.addAttribute("isadmin", isadmin);
		return "DoctorLandingPage";
	}
	
	@GetMapping({"/doctor/appointments/today",
		"/doctor/appointments/upcoming",
		"/doctor/unread/messages",
		"/doctor/patients",
		"/doctor/allsend/messages",
		"/doctor/allrecieve/messages"})
	public String handlingDoctorDetialPage(HttpServletRequest req,Model m,
			@SessionAttribute("docdetails") DoctorDetailedPage ddp,
			@RequestParam(required=false) boolean isadmin)
	{
		String url = req.getRequestURI();
		String reason = "";
		if(url.contains("/appointments/today"))
		{
			reason = "todayappointments";
			m.addAttribute("dataList",ddp.getTodayAppointments());
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/appointments/upcoming"))
		{
			reason = "upcomingappointments";
			m.addAttribute("dataList",ddp.getUpcomingAppointments());
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/unread/messages")) 
		{
			reason = "unreadmessages";
			m.addAttribute("dataList", ddp.getUnreadMessages());
			if(isadmin==false)
			{
				List<Message> unreadMessages = ddp.getUnreadMessages();
				unreadMessages.forEach(msg->msg.setStatus("read"));
				ms.saveAll(unreadMessages);
			}
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/doctor/patients"))
		{
			reason = "doctorpatients";
			m.addAttribute("dataList", ddp.getRelatedPatients());
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/allsend/messages"))
		{
			reason = "doctorsendedMessages";
			m.addAttribute("dataList", ddp.getAllsendMessage());
			List<Message> allsendMessage = ddp.getAllsendMessage();
			if(!allsendMessage.isEmpty())
			{
				ListIterator<Message> i = allsendMessage.listIterator();
				while(i.hasNext())
				{
					Message m1=i.next();
					System.out.println(m1.getReceiver().getName());
				}
			}
			else
			{
				System.out.println(" NO Data is present sorry");
			}
			m.addAttribute("isadmin",isadmin);
		}
		else
		{
			reason = "doctorrecievedMessages";
			m.addAttribute("dataList", ddp.getAllrecieveMessages());
			List<Message> allrecieveMessages = ddp.getAllrecieveMessages();
			if(!allrecieveMessages.isEmpty())
			{
				ListIterator< Message> i = allrecieveMessages.listIterator();
				while(i.hasNext())
				{
					Message m1=i.next();
					System.out.println(m1.getSender().getName());
				}
			}
			else
			{
				System.out.println(" NO Data is present sorry");
			}
			m.addAttribute("isadmin",isadmin);
		}
		m.addAttribute("reason", reason);
		return "doctordetailspage";
	}
	
	
}

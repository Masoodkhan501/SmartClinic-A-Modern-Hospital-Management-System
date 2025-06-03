package com.masood.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.masood.DTO.PatientDTO;
import com.masood.DTO.PatientDetailsPage;
import com.masood.model.Appointment;
import com.masood.model.Message;
import com.masood.model.Patient;
import com.masood.model.PaymentStatus;
import com.masood.model.Role;
import com.masood.model.User;
import com.masood.model.priscription;
import com.masood.service.AppointmentService;
import com.masood.service.MessageService;
import com.masood.service.PatientServiceimpl;
import com.masood.service.PriscriptionServiceImpl;
import com.masood.service.UserImpl;

import jakarta.servlet.http.HttpServletRequest;
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
			Optional<User> userById = us.findUserById(savePatient.getUser_id());
			User u1 = userById.get();
			session.setAttribute("user",u1 );
			session.setAttribute("patient",savePatient);
			page = "redirect:/patient/page";
		} else {
			boolean isrepasscorrect = false;
			m.addAttribute("isrepassword", isrepasscorrect);
			page = "createnewUser";
		}
		return page;
	}
	
	@GetMapping("/check/patient")
	public String loginPatient(@ModelAttribute("User") User u ,
			Model m,HttpSession session,
			@SessionAttribute("dest") String destination)
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
			if(destination.equals("appointmentpage"))
				page="redirect:/book/new/appointment";
			else
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
	
	@GetMapping({"/patient/page","/admin/manage/patient/{id}"})
	public String patientpage(@SessionAttribute("user") User u,
			@SessionAttribute(name="patient",required=false) Patient p,
			@PathVariable(name = "id",required = false) String patientId,
			Model m,HttpServletRequest req,
			HttpSession session)
	{
		Patient p1=new Patient();
		User u1 = new User();
		String url = req.getRequestURI();
		boolean isAdmin = true;
		if(url.contains("/patient/page"))
		{
			p1 = p;
			isAdmin = false;
			Optional<User> user = us.getByEmail(p.getUser_id().getEmail());
			u1 = user.get();
		}
		else
		{
			Optional<Patient> byPatientId = ps.getPatientById(patientId);
			p1 = byPatientId.get();
			isAdmin = true;
			Optional<User> user = us.getByEmail(p1.getUser_id().getEmail());
			u1 = user.get();
		}
		Double price = ps.getTotalAmountofDueBills(p1);
		Math.round(price);
		Byte patientAge = ps.getPatientAge(p1);
		List<Appointment> sizeofAppt = as.getByPatient(p1.getPatient_Id());
		Date d = new Date();
		List<Appointment> upcomingappt = as.getByDateAfter(d);
		List<Message> noofUnread = ms.getMessageByUserandStatus(p1.getUser_id().getId(),"unread");
		List<Appointment> unpaidAppointmentsByPatient = as.getUnpaidAppointmentsByPatient(p1.getPatient_Id());
		List<Message> bySenderId = ms.getBySenderId(p1.getUser_id().getId());
		List<Message> byRecieverId = ms.getByRecieverId(p1.getUser_id().getId());
		PatientDetailsPage pdp = new PatientDetailsPage(sizeofAppt, upcomingappt, noofUnread, unpaidAppointmentsByPatient, byRecieverId, bySenderId);
		session.setAttribute("patientdetailpage", pdp);
		Appointment latestAppointment = as.getLatestAppointmentWhoseappointmentisComplete();
		priscription getlatestpriscription = pres.getlatestpriscription();
		PatientDTO pdto = new PatientDTO(p1, u1);
		pdto.setAppoint(latestAppointment);
		pdto.setPris(getlatestpriscription);
		session.setAttribute("PatientDTO", pdto);
		m.addAttribute("PatientDTO", pdto);
		m.addAttribute("age", patientAge);
		m.addAttribute("lastVisit", latestAppointment);
		m.addAttribute("noofAppointments",sizeofAppt.size());
		m.addAttribute("upcomingAppointments", upcomingappt.size());
		m.addAttribute("noofUnreadMessages",noofUnread.size());
		m.addAttribute("TotalDeu", price);
		boolean isNewPatient = latestAppointment == null && getlatestpriscription == null && sizeofAppt.size() == 0;
		m.addAttribute("isNew", isNewPatient);
		m.addAttribute("isadmin",isAdmin);
		return "patientLandingPage";
	}
	
	@GetMapping("/logout/home")
	public String logoutPatient(Model m,
			HttpSession session)
	{
		session.invalidate();
		return "home";
	}
	
	@GetMapping({"/patient/total/appointments",
		"/patient/upcoming/appointments",
		"/patient/pending/bills",
		"/patient/unread/messages",
		"/patient/allsend/messages",
		"/patient/allrecieve/messages"})
	public String handlingPatientDetailsPage(HttpServletRequest req,Model m,
			@SessionAttribute("patientdetailpage") PatientDetailsPage pdp,
			@RequestParam(required=false) boolean isadmin)
	{
		String url = req.getRequestURI();
		String reason="";
		if(url.contains("/total/appointments"))
		{
			reason = "appointments";
			m.addAttribute("dataList",pdp.getAppointments());
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/upcoming/appointments"))
		{
			reason = "upcomingappointments";
			m.addAttribute("dataList",pdp.getUpcomingappointments());
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/unread/messages")) 
		{
			reason = "unreadmessages";
			m.addAttribute("dataList", pdp.getUnreadMessages());
			if(isadmin==false)
			{
				List<Message> unreadMessages = pdp.getUnreadMessages();
				unreadMessages.forEach(msg->msg.setStatus("read"));
				ms.saveAll(unreadMessages);
			}
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/pending/bills"))
		{
			reason = "unpaidAppointments";
			m.addAttribute("dataList", pdp.getUnpaidappointments());
			m.addAttribute("isadmin",isadmin);
		}
		else if(url.contains("/allsend/messages"))
		{
			reason = "showAllsendmessages";
			m.addAttribute("dataList",pdp.getAllsendMsg() );
			m.addAttribute("isadmin",isadmin);
		}
		else
		{
			reason="showallrecievemessages";
			m.addAttribute("dataList", pdp.getAllrecievedMsg());
			m.addAttribute("isadmin",isadmin);
		}
		m.addAttribute("reason", reason);
		return "patientDetailspage";
	}
	
	@GetMapping("/pay/bills/{apptid}")
	public String paymentdone(@PathVariable("apptid") Appointment app_id,
			@SessionAttribute("patientdetailpage") PatientDetailsPage pdp)
	{
		pdp.getUnpaidappointments()
		.removeIf(a->
		{
			if(a.getApp_id().equals(app_id.getApp_id()))
			{
				a.setPaymentStatus(PaymentStatus.PAID);
				return true;
			}
			return false;
		});
		return "redirect:/patient/pending/bills";
	}
	
	@GetMapping("/pay/bills/all")
	public String paymentall(@SessionAttribute("patientdetailpage") PatientDetailsPage pdp)
	{
		pdp.getUnpaidappointments().forEach(a->a.setPaymentStatus(PaymentStatus.PAID));
		pdp.getUnpaidappointments().clear();
		return "redirect:/patient/pending/bills";
	}
	
}

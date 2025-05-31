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
	
	@GetMapping("/patient/page")
	public String patientpage(@SessionAttribute("user") User u,
			@SessionAttribute("patient") Patient p,Model m,
			HttpSession session)
	{
		Double price = ps.getTotalAmountofDueBills(p);
		Math.round(price);
		Byte patientAge = ps.getPatientAge(p);
		List<Appointment> sizeofAppt = as.getByPatient(p.getPatient_Id());
		Date d = new Date();
		List<Appointment> upcomingappt = as.getByDateAfter(d);
		List<Message> noofUnread = ms.getMessagesByStatus("unread");
		List<Appointment> unpaidAppointmentsByPatient = as.getUnpaidAppointmentsByPatient(p.getPatient_Id());
		List<Message> bySenderId = ms.getBySenderId(p.getUser_id().getId());
		List<Message> byRecieverId = ms.getByRecieverId(p.getUser_id().getId());
		PatientDetailsPage pdp = new PatientDetailsPage(sizeofAppt, upcomingappt, noofUnread, unpaidAppointmentsByPatient, byRecieverId, bySenderId);
		session.setAttribute("patientdetailpage", pdp);
		Appointment latestAppointment = as.getLatestAppointmentWhoseappointmentisComplete();
		priscription getlatestpriscription = pres.getlatestpriscription();
		PatientDTO pdto = new PatientDTO(p, u);
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
			@SessionAttribute("patientdetailpage") PatientDetailsPage pdp)
	{
		String url = req.getRequestURI();
		String reason="";
		if(url.contains("/total/appointments"))
		{
			reason = "appointments";
			m.addAttribute("dataList",pdp.getAppointments());
		}
		else if(url.contains("/upcoming/appointments"))
		{
			reason = "upcomingappointments";
			m.addAttribute("dataList",pdp.getUpcomingappointments());
		}
		else if(url.contains("/unread/messages")) 
		{
			reason = "unreadmessages";
			m.addAttribute("dataList", pdp.getUnreadMessages());
		}
		else if(url.contains("/pending/bills"))
		{
			reason = "unpaidAppointments";
			m.addAttribute("dataList", pdp.getUnpaidappointments());
		}
		else if(url.contains("/allsend/messages"))
		{
			reason = "showAllsendmessages";
			m.addAttribute("dataList",pdp.getAllsendMsg() );
		}
		else
		{
			reason="showallrecievemessages";
			m.addAttribute("dataList", pdp.getAllrecievedMsg());
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

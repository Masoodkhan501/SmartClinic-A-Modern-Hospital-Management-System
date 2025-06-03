package com.masood.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.masood.DTO.AdminDTO;
import com.masood.DTO.AppointmentDetailsDTO;
import com.masood.model.Appointment;
import com.masood.model.AppointmentHistory;
import com.masood.model.Appointmentstatus;
import com.masood.model.Doctor;
import com.masood.model.Message;
import com.masood.model.MessageHistory;
import com.masood.model.OperationNeeded;
import com.masood.model.Patient;
import com.masood.model.User;
import com.masood.model.priscription;
import com.masood.service.AppointmentHistoryImpl;
import com.masood.service.AppointmentService;
import com.masood.service.DoctorSerivce;
import com.masood.service.MessageHistroyImpl;
import com.masood.service.MessageService;
import com.masood.service.PatientServiceimpl;
import com.masood.service.PriscriptionServiceImpl;
//import com.masood.service.UserImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller("AdminController")
public class AdminController 
{


//	@Autowired
//	private UserImpl us;
	@Autowired
	private AppointmentService as;
	@Autowired
	private PriscriptionServiceImpl pres;
	@Autowired
	private DoctorSerivce ds;
	@Autowired
	private MessageService ms;
	@Autowired
	private PatientServiceimpl pats;
	@Autowired
	private AppointmentHistoryImpl aphs;
	@Autowired
	private MessageHistroyImpl mhs;


//	@Autowired
//	private PatientServiceimpl ps;
	
	@GetMapping("/login/admin")
	public String AdminLogin(Model m)
	{
		User u = new User();
		boolean ispassword=true;
		m.addAttribute("ispaword", ispassword);
		m.addAttribute("user", u);
		m.addAttribute("existing", "yes");
		return "admin_login";
	}
	
	@GetMapping("/admin/page")
	public String Adminpage(@SessionAttribute("user")User u, Model m,HttpSession session)
	{
		AdminDTO adto = new AdminDTO(u);
		List<priscription> allPriscription = pres.getAllPriscription();
		adto.setAllprescription(allPriscription);
		List<Doctor> allDoctor = ds.getAllDoctor();
		adto.setL_d(allDoctor);
		List<Patient> allPatient = pats.getAllPatient();
		adto.setL_p(allPatient);
		List<Appointment> allAppointment = as.getallAppointmentInrev();
		adto.setAllAppointment(allAppointment);
		List<Appointment> appointmentByStatus = as.getAppointmentByStatus(Appointmentstatus.PENDING);
		adto.setPendingAppointments(appointmentByStatus);
		List<Message> allMessages = ms.getAllMessages();
		adto.setAllmsg(allMessages);
		List<Message> messagesByStatus = ms.getMessageByUserandStatus(u.getId(),"unread");
		adto.setAllunreadMsg(messagesByStatus);
		List<Message> bySenderId = ms.getBySenderId(u.getId());
		adto.setAllsendMsg(bySenderId);
		List<Message> byRecieverId = ms.getByRecieverId(u.getId());
		adto.setAllrecieveMsg(byRecieverId);
		List<MessageHistory> allHistory = mhs.getAllHistory();
		adto.setAllMsgHis(allHistory);
		List<AppointmentHistory> allhis = aphs.getallAppointmentHistoryDesc();
		adto.setAllappthis(allhis);
		session.setAttribute("AdminDTO", adto);
		m.addAttribute("AdminDTO",adto);
		m.addAttribute("totalAppointments",allAppointment.size());
		m.addAttribute("totalunseenmsg",messagesByStatus.size());
		m.addAttribute("totalPatients",allPatient.size());
		m.addAttribute("totalDoctors",allDoctor.size());
		List<Appointment> recentAppointmentsToShow;
	    if (allAppointment != null && allAppointment.size() > 3) {
	        recentAppointmentsToShow = allAppointment.subList(0, 3); // Get top 5
	    } else {
	        recentAppointmentsToShow = allAppointment; // Get all if 5 or less
	    }
	    m.addAttribute("recentAppointments", recentAppointmentsToShow); // <--- Add this line

	    // Similar for prescriptions:
	    List<priscription> latestPrescriptionsToShow;
	    if (allPriscription != null && allPriscription.size() > 3) {
	        latestPrescriptionsToShow = allPriscription.subList(0, 3); // Get top 5
	    } else {
	        latestPrescriptionsToShow = allPriscription; // Get all if 5 or less
	    }
	    m.addAttribute("latestPrescriptions", latestPrescriptionsToShow);
		return "adminLandingPage";
	}
	
	@GetMapping({"/admin/manage/doctors",
		"/admin/manage/patients",
		"/admin/unread/messages",
		"/admin/manage/appointments",
		"",
		""})
	public String admindetailPage(@SessionAttribute("AdminDTO") AdminDTO adto,
			HttpServletRequest req,Model m)
	{
		String url = req.getRequestURI();
		String reason = "";
		if(url.contains("/manage/appointments"))
		{
			reason = "allappointments";
			m.addAttribute("dataList",adto.getAllAppointment());
		}
		else if(url.contains("/manage/doctors"))
		{
			reason = "alldoctors";
			m.addAttribute("dataList",adto.getL_d());
		}
		else if(url.contains("/unread/messages")) 
		{
			reason = "unreadmessages";
			m.addAttribute("dataList", adto.getAllunreadMsg());
		}
		else if(url.contains("/manage/patients"))
		{
			reason = "allpatients";
			m.addAttribute("dataList", adto.getL_p());
		}
		else if(url.contains("/allsend/messages"))
		{
			reason = "showAllsendmessages";
			m.addAttribute("dataList", adto.getAllsendMsg());
		}
		else
		{
			reason="showallrecievemessages";
			m.addAttribute("dataList", adto.getAllrecieveMsg());
		}
		m.addAttribute("reason", reason);
		m.addAttribute("AdminDTO",adto);
		return "admindetailpage";
	}
	
	@GetMapping({"/admin/appointments/set","/admin/Appointment/fees","/admin/Operation/fees"})
	public String setAppointment(@SessionAttribute("AdminDTO") AdminDTO adto,
			HttpServletRequest req,
			Model m,HttpSession session)
	{
		String uri = req.getRequestURI();
		AppointmentDetailsDTO addto = new AppointmentDetailsDTO();
		List<Appointment> allappoint = adto.getAllAppointment();
		String reason = "";
		if(uri.contains("/appointments/set"))
		{
			List<Appointment> apptwithoutdoc = allappoint.stream()
					.filter(a->a.getDoctor().equals(null)).collect(Collectors.toList());
			reason="settingDoc";
			List<Doctor> allDoctor = ds.getAllDoctor();
			addto.setWithoutDoc(apptwithoutdoc);
			m.addAttribute("Alldoc",allDoctor);
			m.addAttribute("dataList",apptwithoutdoc);
		}
		else if(uri.contains("/appointment/fees"))
		{
			List<Appointment> appwithoutoperation = allappoint.stream()
			.filter(a->a.getOperationRequired().equals(OperationNeeded.NO))
			.collect(Collectors.toList());
			Map<Long, Prescription> prescriptionMap = appwithoutoperation.stream()
			        .map(a -> pres.getByAppointmentId(a.getApp_id()))
			        .filter(Optional::isPresent)
			        .map(Optional::get)
			        .collect(Collectors.toMap(p -> p.getAppointment().getApp_id(), p -> p));
			reason = "settingfees";
			addto.setWithoutOperation(appwithoutoperation);
			m.addAttribute("prescription",prescription);
			m.addAttribute("dataList",appwithoutoperation);
			
		}
		else
		{
			List<Appointment> apptoperationneed = allappoint.stream()
			.filter(a->a.getOperationRequired().equals(OperationNeeded.YES))
			.collect(Collectors.toList());
			reason="settingopfees";
			addto.setWithOperation(apptoperationneed);
			m.addAttribute("dataList",apptoperationneed);
		}
		m.addAttribute("reason",reason);
		session.setAttribute("AppointmentDetailsDTO", addto);
		return "AppointmentSetting";
	}
	
	@PostMapping("/admin/assignDoctorAndDate")
	public String assignDoctorAndDate(@SessionAttribute("AppointmentDetailsDTO") AppointmentDetailsDTO addto,
									@RequestParam Long apptId,
	                                  @RequestParam String doctorId,
	                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date appointmentDate) {

		Optional<Appointment> appoint = as.getAppointmentbyId(apptId);
		Appointment appt = appoint.get();
		Optional<Doctor> doctorById = ds.getDoctorById(doctorId);
		Doctor doctor = doctorById.get();
		
		appt.setD_id(doctor);
		appt.setDateofAppointment(appointmentDate);
		as.saveAppointment(appt);
	    return "redirect:/admin/appointments/set";
	}

}

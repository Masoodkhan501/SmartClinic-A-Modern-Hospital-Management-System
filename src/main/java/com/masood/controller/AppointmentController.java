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

import com.masood.DTO.PatientDetailsPage;
import com.masood.model.Appointment;
import com.masood.model.AppointmentHistory;
import com.masood.model.Appointmentstatus;
import com.masood.model.Patient;
import com.masood.model.PaymentStatus;
import com.masood.model.User;
import com.masood.model.priscription;
import com.masood.service.AppointmentHistoryImpl;
import com.masood.service.AppointmentService;
import com.masood.service.PatientServiceimpl;
import com.masood.service.PriscriptionServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller("AppointmentController")
public class AppointmentController 
{


	@Autowired
	private AppointmentService as;
	@Autowired
	private AppointmentHistoryImpl ahi;
	@Autowired
	private PatientServiceimpl ps;
	@Autowired
	private PriscriptionServiceImpl pres;


	@GetMapping("/book/new/appointment")
	public String newAppointment(@SessionAttribute("user") User u,
			@SessionAttribute("patient") Patient p,Model m)
	{
		Appointment a = new Appointment();
		Optional<Patient> patientById = ps.getPatientById(p.getPatient_Id());
		Patient patient = patientById.get();
		a.setP_id(patient);
		m.addAttribute("appointment",a);
		m.addAttribute("isnew",true);
		return "Appointment";
	}
	
	@PostMapping("/accept/appointment")
	public String saveAppointment(@ModelAttribute("appointment") Appointment appt,
			@RequestParam String by)
	{
		
		appt.setStatus(Appointmentstatus.PENDING);
		appt.setPaymentStatus(PaymentStatus.UNPAID);
		Appointment saveAppointment = as.saveAppointment(appt);
		AppointmentHistory ah = new AppointmentHistory();
		ah.setAppoint_id(saveAppointment);
		ah.setOldNotes(saveAppointment.getNotes());
		ah.setChangedBy(by);
		ah.setDate_changed();
		ahi.save(ah);
		return "redirect:/patient/page";
	}
	
	 @GetMapping("/appointment/update/{app_id}")
	    public String editAppointment(@PathVariable("app_id") Long appointmentId,
	                                  Model m) {
	        Optional<Appointment> optionalAppt = as.getAppointmentbyId(appointmentId);

	        if (optionalAppt.isPresent()) {
	            Appointment existingAppointment = optionalAppt.get();
	            m.addAttribute("appointment", existingAppointment);
	            m.addAttribute("isnew",false);
	            return "Appointment"; 
	        } else {
	            return "redirect:/patient/page";
	        }
	    }
	
	@PostMapping("/update/appointment")
	public String updateAppointment(@ModelAttribute Appointment appointment, 
	                                @SessionAttribute("user") User currentUser) {
		Optional<Appointment> Appt = as.getAppointmentbyId(appointment.getApp_id());
	    Appointment existingAppt = Appt.get();
	    if (!existingAppt.getNotes().equals(appointment.getNotes())) {
	        AppointmentHistory ah = new AppointmentHistory();
	        ah.setAppoint_id(existingAppt);
	        ah.setOldNotes(existingAppt.getNotes());
	        ah.setChangedBy(currentUser.getRole().toString());
	        ah.setDate_changed(); 

	        ahi.save(ah);
	    }

	    existingAppt.setNotes(appointment.getNotes());

	    as.saveAppointment(existingAppt);

	    return "redirect:/patient/page";
	}
	
	@GetMapping({"/appointment/delete/{app_id}","/admin/appointments/delete/{app_id}"})
	public String AppointmentDelete(@PathVariable Long app_id,
			@SessionAttribute("patientdetailpage") PatientDetailsPage pdp)
	{
		Optional<Appointment> appointmentbyId = as.getAppointmentbyId(app_id);
		Appointment appointment = new Appointment();
		if(appointmentbyId.isPresent())
		{
			as.deleteAppointmentById(app_id);
			appointment = appointmentbyId.get();
		}
		pdp.getAppointments().remove(appointment);
		return "redirect:/patient/total/appointments";
	}

	@GetMapping("/admin/appointments/update/{id}")
	public String updateDate(@PathVariable Long id,Model m)
	{
		Optional<Appointment> appointmentbyId = as.getAppointmentbyId(id);
		Appointment appointment = appointmentbyId.get();
		m.addAttribute("appt", appointment);
		return "appointmentChange";
	}
	
	@PostMapping("/admin/update/appointmentDate")
	public String saveUpdateAppointment(@ModelAttribute Appointment appt)
	{
		Optional<Appointment> appointmentbyId = as.getAppointmentbyId(appt.getApp_id());
		Appointment appointment = appointmentbyId.get();
		appointment.setDateofAppointment(appt.getDateofAppointment());
		as.saveAppointment(appointment);
		return "redirect:/admin/manage/appointments";
	}
	
	@GetMapping({"/patient/appointment/delete/{app_id}",
		"/doctor/appointment/delete/{app_id}"})
	public String deleteAppointment(@PathVariable Long app_id,HttpServletRequest req)
	{
		Optional<Appointment> appointmentbyId = as.getAppointmentbyId(app_id);
		Appointment appointment = appointmentbyId.get();
		Optional<priscription> byAppointmentId = pres.getByAppointmentId(app_id);
		priscription priscription = byAppointmentId.get();
		pres.deletePriscriptionById(priscription.getId());
		as.deleteAppointmentById(appointment.getApp_id());
		String requestURI = req.getRequestURI();
		if(requestURI.contains("/patient/appointment/"))
			return "redirect:/patient/page";
		else
			return "redirect:/doctor/page";
	}
	
}

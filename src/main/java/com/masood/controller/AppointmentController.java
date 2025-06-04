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

import com.masood.DTO.AppointmentDTO;
import com.masood.model.Appointment;
import com.masood.model.AppointmentHistory;
import com.masood.model.Appointmentstatus;
import com.masood.model.Patient;
import com.masood.model.PaymentStatus;
import com.masood.model.User;
import com.masood.service.AppointmentHistoryImpl;
import com.masood.service.AppointmentService;

@Controller("AppointmentController")
public class AppointmentController 
{

	@Autowired
	private AppointmentService as;
	@Autowired
	private AppointmentHistoryImpl ahi;

	@GetMapping("/book/new/appointment")
	public String newAppointment(@SessionAttribute("user") User u,
			@SessionAttribute("patient") Patient p,Model m)
	{
		Appointment a = new Appointment();
		AppointmentDTO adto = new AppointmentDTO(p, u, a);
		m.addAttribute("appointmentdto",adto);
		return "Appointment";
	}
	
	@PostMapping("/accept/appointment")
	public String saveAppointment(@ModelAttribute("appointmentdto") AppointmentDTO adto,
			@RequestParam String by,
			@SessionAttribute("patient") Patient p)
	{
		Appointment a = adto.getAppt();
		a.setP_id(p);
		a.setStatus(Appointmentstatus.PENDING);
		a.setPaymentStatus(PaymentStatus.UNPAID);
		Appointment saveAppointment = as.saveAppointment(a);
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
	                                  @SessionAttribute("user") User u,
	                                  @SessionAttribute("patient") Patient p,
	                                  Model m) {
	        Optional<Appointment> optionalAppt = as.getAppointmentbyId(appointmentId);

	        if (optionalAppt.isPresent()) {
	            Appointment existingAppointment = optionalAppt.get();
	            // Create a DTO to pre-fill the form
	            AppointmentDTO adto = new AppointmentDTO(p, u, existingAppointment);
	            m.addAttribute("appointmentdto", adto);
	            return "Appointment"; // Use the same form template for editing
	        } else {
	            return "redirect:/patient/page";
	        }
	    }
	
	@PostMapping("/update/appointment")
	public String updateAppointment(@ModelAttribute("appointmentdto") AppointmentDTO dto, 
	                                @SessionAttribute("user") User currentUser) {

	    Optional<Appointment> Appt = as.getAppointmentbyId(dto.getAppt().getApp_id());
	    Appointment existingAppt = Appt.get();
	    // Only create history if notes changed
	    if (!existingAppt.getNotes().equals(dto.getAppt().getNotes())) {
	        AppointmentHistory ah = new AppointmentHistory();
	        ah.setAppoint_id(existingAppt);
	        ah.setOldNotes(existingAppt.getNotes());
	        ah.setChangedBy(currentUser.getRole().toString());
	        ah.setDate_changed(); // will set new Date()

	        ahi.save(ah);
	    }

	    // Now update the actual appointment
	    existingAppt.setNotes(dto.getAppt().getNotes());

	    as.saveAppointment(existingAppt);

	    return "redirect:/patient/page";
	}

}

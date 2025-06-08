package com.masood.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.masood.DTO.DoctorDetailedPage;
import com.masood.model.Appointment;
import com.masood.model.Appointmentstatus;
import com.masood.model.OperationNeeded;
import com.masood.model.priscription;
import com.masood.service.AppointmentService;
import com.masood.service.PriscriptionServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller("perscriptionController")
public class PrescriptionController
{

	@Autowired
	private AppointmentService as;
	@Autowired
	private PriscriptionServiceImpl ps;

	@GetMapping("/prescription/create/{id}")
	public String createPriscription(Model m,@PathVariable Long id)
	{
		Optional<Appointment> appointmentbyId = as.getAppointmentbyId(id);
		Appointment appointment = appointmentbyId.get();
		m.addAttribute("appt",appointment);
		priscription p = new priscription();
		m.addAttribute("pres",p);
		List<OperationNeeded> options = Arrays.asList(OperationNeeded.values());
		m.addAttribute("Operationrequirement", options);
		return "prescriptionpage";
	}
	
	@PostMapping("/doctor/save/prescription/{appt_id}")
	public String savePrescription(@PathVariable Long appt_id,
			@ModelAttribute("pres") priscription prescription,
			@SessionAttribute("docdetails") DoctorDetailedPage ddp)
	{
		
		Optional<Appointment> appointmentbyId = as.getAppointmentbyId(appt_id);
		Appointment appt = appointmentbyId.get();
		prescription.setAppointid(appt);
		appt.setStatus(Appointmentstatus.DONE);
		as.saveAppointment(appt);
		ps.savePriscription(prescription);
		List<Appointment> todayAppointments = ddp.getTodayAppointments();
		todayAppointments.remove(appt);
		return "redirect:/doctor/appointments/today";
	}
	
	@GetMapping({"/patient/prescriptions/delete/{id}",
		"/admin/prescriptions/delete/{id}"})
	public String deletePrescription(@PathVariable Long id,HttpServletRequest req)
	{
		Optional<priscription> byid = ps.getByid(id);
		priscription priscription = byid.get();
		ps.deletePriscriptionById(priscription.getId());
		String requestURI = req.getRequestURI();
		if(requestURI.contains("/patient/prescriptions"))
			return "redirect:/patient/page";
		else
			return "redirect:/admin/page";
	}
}

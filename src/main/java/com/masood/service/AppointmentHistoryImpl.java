package com.masood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.AppointmentHistory;
import com.masood.repository.AppointmentHistoryRepo;
@Service("AppointmentHistoryImpl")
public class AppointmentHistoryImpl implements AppointmentHistoryInterface {

	@Autowired
	private AppointmentHistoryRepo ahr;
	
	public AppointmentHistory save(AppointmentHistory history) {
		return ahr.save(history);
	}

	public List<AppointmentHistory> findAll() {
		return ahr.findAll();
	}

	public List<AppointmentHistory> findByAppointmentId(Long appointmentId) {
		return ahr.findAll()
                .stream()
                .filter(h -> h.getAppoint_id() != null && h.getAppoint_id().getApp_id().equals(appointmentId))
                .toList();
	}

	public void deleteAppointmentById(Long id) {
		ahr.deleteById(id);
	}

}

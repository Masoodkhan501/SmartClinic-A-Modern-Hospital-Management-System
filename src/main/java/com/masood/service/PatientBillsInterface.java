package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.PatientBills;
import com.masood.model.PaymentStatus;

public interface PatientBillsInterface 
{
	public PatientBills savePatientBills(PatientBills pb);
	public Optional<PatientBills> getById(Long id);
	public List<PatientBills> getAll();
	public void deleteById(Long id);
	public void DeleteAllbillsByPatientId(String id);
	public List<PatientBills> getAllBillsByPatientId(String id);
	public List<PatientBills> getAllBillsByPatientName(String name);
	public List<PatientBills> getByPaymentStatus(PaymentStatus status);
}

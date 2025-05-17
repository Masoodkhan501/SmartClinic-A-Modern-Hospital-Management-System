package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.DoctorBills;

public interface DocBillsInterface
{
	public DoctorBills saveBills(DoctorBills dbills);
	public Optional<DoctorBills> getById(Long id);
	public List<DoctorBills> getAllBills();
	public void DeleteById(Long id);
	public void DeleteAllbillsByDoctorId(String id);
	public List<DoctorBills> getAllBillsByDoctorId(String id);
	public List<DoctorBills> getAllBillsByDoctorName(String name);
}

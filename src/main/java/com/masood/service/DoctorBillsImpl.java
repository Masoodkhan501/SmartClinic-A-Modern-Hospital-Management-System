package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.DoctorBills;
import com.masood.repository.DoctorpaymentRepo;
@Service("DoctorBillsservice")
public class DoctorBillsImpl implements DocBillsInterface 
{
	@Autowired
	private DoctorpaymentRepo dp;

	public DoctorBills saveBills(DoctorBills dbills) {
		return dp.save(dbills);
	}

	public Optional<DoctorBills> getById(Long id) 
	{
		return dp.findById(id);
	}

	public List<DoctorBills> getAllBills() {
		return dp.findAll();
	}

	public void DeleteById(Long id) {
		dp.deleteById(id);
	}

	public void DeleteAllbillsByDoctorId(String id) {
		dp.DeleteBillsByDoctorId(id);
	}

	public List<DoctorBills> getAllBillsByDoctorId(String id) {
		return dp.findByDoctorId(id);
	}

	public List<DoctorBills> getAllBillsByDoctorName(String name) {
		return dp.findBillsByDoctorNameLike(name);
	}

}

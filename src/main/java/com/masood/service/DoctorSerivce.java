package com.masood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Doctor;
import com.masood.repository.DoctorRepoInterface;
@Service("Doctor_service")
public class DoctorSerivce implements DoctorServiceInterface
{
	@Autowired
	private DoctorRepoInterface dr;
	
	public Doctor saveDoctor(Doctor d) 
	{
		String lastEntry = dr.findLastEntry();
		String id = "";
		if(lastEntry!=null)
		{
			String last3Digit = lastEntry.substring(2);
			Integer lastNumber = Integer.parseInt(last3Digit);
			lastNumber++;
			id= String.format("DOC%03d", lastNumber);
		}
		else
		{
			id="DOC001";
		}
		d.setDoc_id(id);
		Doctor save = dr.save(d);
		return save;
	}

	@Override
	public Doctor getDoctorById(Doctor d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int DeleteDoctorById(Doctor d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Doctor getByEmail(Doctor d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getBySpecialization(String specialization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getTop5Doctors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getByNameAndSpecialization(String name, String specialization) {
		// TODO Auto-generated method stub
		return null;
	}

}

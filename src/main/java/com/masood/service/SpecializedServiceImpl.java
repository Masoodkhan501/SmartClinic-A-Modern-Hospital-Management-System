package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Specialized;
import com.masood.repository.SpecilizedRepo;
@Service("SpecializationService")
public class SpecializedServiceImpl implements SpecilaizedInterface
{
	@Autowired
	private SpecilizedRepo spr;
	
	public Specialized saveSpecialized(Specialized s) {
		return spr.save(s);
	}

	public Optional<Specialized> getById(String id) {
		return spr.findById(id);
	}

	public List<Specialized> getAllSpecialization() {
		return spr.findAll();
	}

	public void deleteSpecializationByid(String id) {
		spr.deleteById(id);
	}

	public void deleteByspecialization(String name) {
		spr.deleteSpecializationByName(name);
	}

	public Optional<Specialized> getByspecialization(String name) {
		return spr.findBySpecialization(name);
	}

	public List<Specialized> getByDoctorId(String id) {
		return spr.findByDoctorId(id);
	}

	public List<Specialized> getByDoctorName(String name) {
		return spr.findByDoctorName(name);
	}

}

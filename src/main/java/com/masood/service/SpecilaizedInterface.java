package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.Specialized;

public interface SpecilaizedInterface
{
	public Specialized saveSpecialized(Specialized s);
	public Optional<Specialized> getById(String id);
	public List<Specialized> getAllSpecialization();
	public void deleteSpecializationByid(String id);
	public void deleteByspecialization(String name);
	public Optional<Specialized> getByspecialization(String name);
	public List<Specialized> getByDoctorId(String id);
	public List<Specialized> getByDoctorName(String name);
}

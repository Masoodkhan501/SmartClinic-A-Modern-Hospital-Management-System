package com.masood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Disease;
import com.masood.repository.DiseaseRepo;

import jakarta.transaction.Transactional;
@Service("DiseaseImpl")
@Transactional
public class DiseaseImpl implements DiseaseInterface 
{
	@Autowired
	private DiseaseRepo di;
	
	public Disease saveDisease(Disease d) 
	{
		return di.save(d);
	}

	public Optional<Disease> getById(Long id)
	{
		return di.findById(id);
	}

	public List<Disease> getAll()
	{
		return di.findAll();
	}

	public void deleteByName(String name) 
	{
		di.deleteByName(name);
	}

	public void deleteByid(Long id)
	{
		di.deleteById(id);
	}

	public Disease getByName(String name) 
	{
		return di.findDiseaseByName(name);
	}

	public List<Disease> getByDescription(String description)
	{
		return di.findBydescription(description);
	}

}

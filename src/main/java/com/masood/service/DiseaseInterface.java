package com.masood.service;

import java.util.List;
import java.util.Optional;

import com.masood.model.Disease;

public interface DiseaseInterface
{
	public Disease saveDisease(Disease d);
	public Optional<Disease> getById(Long id);
	public List<Disease> getAll();
	public void deleteByName(String name);
	public void deleteByid(Long id);
	public Disease getByName(String name);
	public List<Disease> getByDescription(String description);
}

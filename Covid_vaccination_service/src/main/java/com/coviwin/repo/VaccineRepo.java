package com.coviwin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.Vaccine;

public interface VaccineRepo extends JpaRepository<Vaccine,Integer>{
 
	public List<Vaccine> findByVaccineName(String vaccineName);
}

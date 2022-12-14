package com.coviwin.service;

import java.util.List;

import com.coviwin.exception.VaccineException;
import com.coviwin.model.Vaccine;

public interface VaccineService {


	public List<Vaccine> allVaccine()throws VaccineException;
	public List<Vaccine> getVaccineByName( String vaccineName )throws VaccineException;
	public Vaccine getVaccineById(Integer vaccineId)throws VaccineException;
	public Vaccine addVaccine(Vaccine vaccine)throws VaccineException;
	public Vaccine updateVaccine(Vaccine vaccine)throws VaccineException;
	public Boolean deleteVaccine(Vaccine vaccine )throws VaccineException;

	
}

package com.coviwin.service;

import java.util.List;

import com.coviwin.exception.VaccineException;
import com.coviwin.model.Vaccine;

public interface VaccineService {

<<<<<<< HEAD
	
	public Vaccine allVaccine();
	public Vaccine getVaccineByName( String vaccineName );//m
	public Vaccine getVaccineById(Integer vaccineId);//m
	public Vaccine addVaccine(Vaccine vaccine);
	public Vaccine updateVaccine(Vaccine vaccine);
	public Boolean deleteVaccine(Vaccine vaccine );
=======
	public List<Vaccine> allVaccine()throws VaccineException;
	public List<Vaccine> getVaccineByName( String vaccineName )throws VaccineException;
	public Vaccine getVaccineById(Integer vaccineId)throws VaccineException;
	public Vaccine addVaccine(Vaccine vaccine)throws VaccineException;
	public Vaccine updateVaccine(Vaccine vaccine)throws VaccineException;
	public Boolean deleteVaccine(Vaccine vaccine )throws VaccineException;
>>>>>>> 4fccf12cc1f5297f53f954949241415efa64714d
	
}

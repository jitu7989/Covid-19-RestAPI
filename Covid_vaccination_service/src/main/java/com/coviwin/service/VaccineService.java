package com.coviwin.service;

import com.coviwin.model.Vaccine;

public interface VaccineService {

	public Vaccine allVaccine();
	public Vaccine getVaccineByName( String vaccineName );
	public Vaccine getVaccineById(Integer vaccineId);
	public Vaccine addVaccine(Vaccine vaccine);
	public Vaccine updateVaccine(Vaccine vaccine);
	public Boolean deleteVaccine(Vaccine vaccine );
	
}

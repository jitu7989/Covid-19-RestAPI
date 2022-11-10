package com.coviwin.service;

public interface VaccineService {

	public Vaccine allVaccine();
	public Vaccine getVaccineByName( String vaccineName );
	public Vaccine getVaccineById(Integer vaccineId);
	public Vaccine addVaccine(Vaccine vaccine);
	public Vaccine updateVaccine(Vaccine vaccine);
	public Boolean deleteVaccine(Vaccine vaccine );
	
}

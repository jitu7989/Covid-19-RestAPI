package com.coviwin.service;

import java.util.List;

public interface VaccinationCenterService {

	public List<VaccinationCenter> getAllVaccineCenters( );
	public VaccinationCenter getVaccineCenters(Integer centerid);
	public VaccinationCenter addVaccineCenter(VaccinationCenter center);
	public VaccinationCenter updateVaccineCenter(VaccinationCenter center);
	public VaccinationCenter deleteVaccineCenter(VaccinationCenter center);
	
	
}

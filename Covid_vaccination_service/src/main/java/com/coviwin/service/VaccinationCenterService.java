package com.coviwin.service;

import java.util.List;

import com.coviwin.exception.VaccinationCenterException;
import com.coviwin.model.VaccinationCenter;

public interface VaccinationCenterService {

	public List<VaccinationCenter> getAllVaccineCenters( )throws VaccinationCenterException;
	public VaccinationCenter getVaccineCenters(Integer centerid)throws VaccinationCenterException;
	public VaccinationCenter addVaccineCenter(VaccinationCenter center)throws VaccinationCenterException;
	public VaccinationCenter updateVaccineCenter(VaccinationCenter center)throws VaccinationCenterException;
	public VaccinationCenter deleteVaccineCenter(VaccinationCenter center)throws VaccinationCenterException;
	
	
}

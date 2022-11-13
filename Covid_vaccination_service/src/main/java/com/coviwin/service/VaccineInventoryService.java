package com.coviwin.service;

import java.time.LocalDate;
import java.util.List;

import com.coviwin.exception.VaccineException;
import com.coviwin.exception.VaccineInventoryException;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineCount;
import com.coviwin.model.VaccineInventory;

public interface VaccineInventoryService {

	public List<VaccineInventory> allVaccineInventory()throws VaccineInventoryException;
	
	public VaccineInventory addVaccineCount(Integer vid,Integer vacInv, VaccineCount vacineCount)throws VaccineInventoryException, VaccineException ;
	
	public VaccineInventory addVaccineInventoryByCenter(Integer centerId,VaccineInventory vInventory)throws VaccineInventoryException;
	
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory,VaccineInventory vInventory)throws VaccineInventoryException;
	
	public Boolean deleteVaccineInventory(Integer vaccineInventory)throws VaccineInventoryException;
	
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date)throws VaccineInventoryException;
	
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine)throws VaccineInventoryException;

	
}

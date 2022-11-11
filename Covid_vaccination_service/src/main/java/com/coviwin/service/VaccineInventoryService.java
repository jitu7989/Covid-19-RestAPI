package com.coviwin.service;

import java.time.LocalDate;
import java.util.List;

import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineInventory;

public interface VaccineInventoryService {

	public List<VaccineInventory> allVaccineInventory();
	public VaccineInventory addVaccineCount(VaccinationCenter vCenter, Vaccine vacine , Integer count);
	public VaccineInventory addVaccineInventoryByCenter(Integer centerId,VaccineInventory vInventory);
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory,VaccineInventory vInventory);
	public Boolean deleteVaccineInventory(Integer vaccineInventory);
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date);
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine);
	
}

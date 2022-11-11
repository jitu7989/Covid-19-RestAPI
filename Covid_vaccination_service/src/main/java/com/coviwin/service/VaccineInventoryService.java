package com.coviwin.service;

import java.time.LocalDate;
import java.util.List;

import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineInventory;

public interface VaccineInventoryService {
    // admin
	public List<VaccineInventory> allVaccineInventory();
	public VaccineInventory addVaccineCount();
	public VaccineInventory addVaccineIncentoryByCenter(Integer centerId);
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory);
	public Boolean deleteVaccineInventory(Integer vaccineInventory);
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date);
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine);
	
}

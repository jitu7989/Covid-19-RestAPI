package com.coviwin.service;

import java.util.List;

public interface VaccineInventoryService {

	public List<VaccineInventory> allVaccineInventory();
	public VaccineInventory addVaccineCount();
	public VaccineInventory addVaccineIncentoryByCenter(Integer centerId);
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory);
	public Boolean deleteVaccineInventory(Integer vaccineInventory);
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date);
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine);
	
}

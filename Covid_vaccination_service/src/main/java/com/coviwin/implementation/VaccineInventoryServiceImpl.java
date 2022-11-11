package com.coviwin.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coviwin.exception.VaccineInventoryException;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineCount;
import com.coviwin.model.VaccineInventory;
import com.coviwin.repo.VaccineInventoryRepo;
import com.coviwin.service.VaccineInventoryService;

public class VaccineInventoryServiceImpl implements VaccineInventoryService {

	@Autowired
	private VaccineInventoryRepo vacInvRepo;
	
	
	@Override
	public List<VaccineInventory> allVaccineInventory()throws VaccineInventoryException {
		
		List<VaccineInventory> list = vacInvRepo.findAll();
		
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory Found..");
		}else
			return list;
	}

	@Override
	public VaccineInventory addVaccineCount(VaccineInventory vacInv, VaccineCount vacineCount)throws VaccineInventoryException {

		VaccineInventory vaccineInventory = vacInvRepo.findById(vacInv.getVaccineInventoryId())
				                            .orElseThrow(() -> new VaccineInventoryException("No VaccineInventory found with details : "+ vacInv));
		
	vacineCount.setVaccineInventory(vacInv); // associating vacineCount with VaccineInventory
	
	vaccineInventory.getVaccineCounts().add(vacineCount);
	
	return vacInvRepo.save(vaccineInventory);
	
	}

	
	@Override
	public VaccineInventory addVaccineInventoryByCenter(Integer centerId, VaccineInventory vInventory)
			throws VaccineInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory, VaccineInventory vInventory)
			throws VaccineInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteVaccineInventory(Integer vaccineInventory) throws VaccineInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date) throws VaccineInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine) throws VaccineInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

}

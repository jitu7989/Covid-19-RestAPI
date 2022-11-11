package com.coviwin.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.VaccineInventoryException;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineCount;
import com.coviwin.model.VaccineInventory;
import com.coviwin.repo.VaccinationCenterRepo;
import com.coviwin.repo.VaccineInventoryRepo;
import com.coviwin.service.VaccineInventoryService;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {

	@Autowired
	private VaccineInventoryRepo vacInvRepo;
	
	@Autowired
	private VaccinationCenterRepo vacCenterRepo;
	
	
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
		
		VaccinationCenter vaccinationCenter = vacCenterRepo.findById(centerId)
				                              .orElseThrow(() -> new VaccineInventoryException("No VaccinationCenter found with centerId : "+ centerId)) ;
	
	    vaccinationCenter.setVaccineInventory(vInventory); // associating vaccinationCenter with VaccineInventory
	    
	    List<VaccineCount> VacCountList = vInventory.getVaccineCounts();
	    
	    for(VaccineCount vacCount : VacCountList) {
	    	
	    	vacCount.setVaccineInventory(vInventory); // associating each VaccineCount with VaccineInventory
	    }
	    
	    
	    vInventory.getVaccinationCenters().add(vaccinationCenter);
	    
	    return vacInvRepo.save(vInventory);
	
	}

	@Override
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory, VaccineInventory vInventory)
			throws VaccineInventoryException {

          vacInvRepo.findById(vaccineInventory).orElseThrow(() -> new VaccineInventoryException("No VaccineInventory found with Id : "+vaccineInventory));
          
          
          List<VaccinationCenter> centerList = vInventory.getVaccinationCenters();
          
          for(VaccinationCenter vacCenter : centerList) {
        	  
        	  vacCenter.setVaccineInventory(vInventory); // associating each VaccinationCenter with updated VaccineInventory
          }
          
          
          List<VaccineCount> VacCountList = vInventory.getVaccineCounts();
  	    
  	    for(VaccineCount vacCount : VacCountList) {
  	    	
  	    	vacCount.setVaccineInventory(vInventory); // associating each VaccineCount with updated VaccineInventory
  	    }
  	    
          
          return vacInvRepo.save(vInventory);
          
	}

	@Override
	public Boolean deleteVaccineInventory(Integer vaccineInventory) throws VaccineInventoryException {

        vacInvRepo.findById(vaccineInventory).orElseThrow(() -> new VaccineInventoryException("No VaccineInventory found with Id : "+vaccineInventory));

        vacInvRepo.deleteById(vaccineInventory);
        
        return true;
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date) throws VaccineInventoryException {
		
		List<VaccineInventory> list = vacInvRepo.findBydate(date);
		
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory found with data : " + date);
		}else
			return list;
		
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine) throws VaccineInventoryException {
		
		List<VaccineInventory> result = new ArrayList<>();
		
		List<VaccineInventory> list = vacInvRepo.findAll();
		
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory found, first add some Inventory");
			
		}
			
			for(VaccineInventory vacInv : list) {
				
			  List<VaccineCount> vacCountList =	vacInv.getVaccineCounts();
			  
			  if(vacCountList.isEmpty()) {
				   throw new VaccineInventoryException("No VaccineCount found, first add some VaccineCount");
			  
			  }
				  
				  for(VaccineCount vacCount : vacCountList) {
					  
					  if(vacCount.getVaccine() == vaccine) {
						  result.add(vacInv);
					  }
					  
				  }
				
			}
			
		
		
		if(result.isEmpty()) {
			   throw new VaccineInventoryException("No VaccineInventory found with Vaccine : " + vaccine);

		}else
			return result;
		
	}

}

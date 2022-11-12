package com.coviwin.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.VaccinationCenterException;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.repo.VaccinationCenterRepo;
import com.coviwin.service.VaccinationCenterService;


@Service
public class VaccinationCenterServiceImpl  implements VaccinationCenterService{

	@Autowired
	VaccinationCenterRepo vcrepo;

	@Override
	public List<VaccinationCenter> getAllVaccineCenters() throws VaccinationCenterException {
		List<VaccinationCenter> vcall =vcrepo.findAll();
		if(vcall.size()==0)
			throw new VaccinationCenterException("There are no centers available");
		
		return vcall;
	}

	@Override
	public VaccinationCenter getVaccineCenters(Integer centerid) throws VaccinationCenterException {
	java.util.Optional<VaccinationCenter> op = vcrepo.findById(centerid);
	if(op.isPresent())
		return op.get();
	
	throw new VaccinationCenterException("No center found");
	}

	@Override
	public VaccinationCenter addVaccineCenter(VaccinationCenter center) throws VaccinationCenterException {
		
		VaccinationCenter vc = vcrepo.save(center);
		if(vc==null)
		throw new VaccinationCenterException("Unable to save");
		
		return vc;
	}

	@Override
	public VaccinationCenter updateVaccineCenter(VaccinationCenter center) throws VaccinationCenterException {
		Optional<VaccinationCenter> op = vcrepo.findById(center.getCode());
	    if(op.isPresent())
	    {
	    	VaccinationCenter oldvc = op.get();
	    	oldvc.setAddress(center.getAddress());
	    	oldvc.setAppointments(center.getAppointments());
	    	oldvc.setCentername(center.getCentername());
	    	oldvc.setCity(center.getCity());
	    	oldvc.setPincode(center.getPincode());
	    	oldvc.setState(center.getState());
	    	oldvc.setVaccineInventory(center.getVaccineInventory());
	      return oldvc;
	    }	
	    
	    throw new VaccinationCenterException("No center found to update");
	}

	@Override
	public VaccinationCenter deleteVaccineCenter(VaccinationCenter center) throws VaccinationCenterException {
		Optional<VaccinationCenter> op = vcrepo.findById(center.getCode());
	    if(op.isPresent())
	    {
	    	vcrepo.deleteById(center.getCode());
	    	return op.get();
	    }
	    
		throw new VaccinationCenterException("No Center found to update");
	}
	
	
	
	

}

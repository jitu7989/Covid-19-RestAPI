package com.coviwin.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.VaccineException;
import com.coviwin.model.Vaccine;
import com.coviwin.repo.VaccineRepo;
import com.coviwin.service.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	VaccineRepo vaccineRepo;
	
	
	@Override
	public List<Vaccine> allVaccine() throws VaccineException {
		List<Vaccine> vcall = vaccineRepo.findAll();
		if(vcall.size()==0)
			 throw new VaccineException("Empty Inventory !!!");
		
		return vcall;
	}

	
	// How is this possible to get a single vaccine from vaccine Name
	// so I change it to List of Vaccines
	@Override
	public List<Vaccine> getVaccineByName(String vaccineName) throws VaccineException {
		List<Vaccine> vc= vaccineRepo.findByVaccineName(vaccineName);
		if(vc.isEmpty())
			throw new VaccineException("No vaccine found");
		return vc;
	}

	@Override
	public Vaccine getVaccineById(Integer vaccineId) throws VaccineException {
		Optional<Vaccine> op = vaccineRepo.findById(vaccineId);
		if(op.isPresent())
			return op.get();
		throw new VaccineException("No exception found with this Id");
	}

	@Override
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineException {
		
		Vaccine vac = vaccineRepo.save(vaccine);
		if(vac==null)
			 throw new VaccineException("Unable to save ");
		return vac;
	}

	@Override
	public Vaccine updateVaccine(Vaccine vaccine) throws VaccineException {
		Optional<Vaccine> op = vaccineRepo.findById(vaccine.getVaccineid());
		if(op.isPresent())
		{
			Vaccine vac = op.get();
			vac.setDescription(vaccine.getDescription());
			vac.setMember(vaccine.getMember());
			vac.setVaccinecount(vaccine.getVaccinecount());
			vac.setVaccineName(vaccine.getVaccineName());
			
			return vac;
		}
		throw new VaccineException("Unable to update");
	}

	@Override
	public Boolean deleteVaccine(Vaccine vaccine) throws VaccineException {
		
		Optional<Vaccine> op = vaccineRepo.findById(vaccine.getVaccineid());
		if(op.isPresent())
		{
			vaccineRepo.deleteById(vaccine.getVaccineid());
			return true;
		}
		throw new VaccineException("Unable to Delete");
		
	}

	

	

}
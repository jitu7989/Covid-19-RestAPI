package com.coviwin.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.VaccineRegistrationException;
import com.coviwin.model.Member;
import com.coviwin.model.VaccineRegistration;
import com.coviwin.repo.VaccineRegistrationRepo;
import com.coviwin.service.VaccineRegistrationService;

@Service
public class VaccineRegistrationSerivceImpl implements VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationRepo vacRegRepo;
	
	@Override
	public List<VaccineRegistration> getAllVaccineRegistration()throws VaccineRegistrationException {
	
		List<VaccineRegistration> list = vacRegRepo.findAll();
		
		if(list.isEmpty()){
			throw new VaccineRegistrationException("No data found..");
		}else
			return list;
	}
	
	@Override
	public VaccineRegistration getVaccineRegistration(Long mobileNo) throws VaccineRegistrationException {

       return vacRegRepo.findById(mobileNo).orElseThrow(() -> new VaccineRegistrationException("No VaccineRegistration found with mobileNO : " + mobileNo));
	}

	@Override
	public List<Member> getAllMember(Long mobileNo) throws VaccineRegistrationException {

          VaccineRegistration vacReg = vacRegRepo.findById(mobileNo).orElseThrow(() -> new VaccineRegistrationException("No VaccineRegistration found with mobileNO : " + mobileNo));
          
          return vacReg.getMember();
	}

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException {
		// TODO Auto-generated method stub
		return null;
	}

}

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

import lombok.Data;

@Data
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


		
		List<Member> list = vacRegRepo.getMembersByMobileNo(mobileNo);
		
		if(list.isEmpty()) {
			throw new VaccineRegistrationException("NO member found with mobileNo : " + mobileNo);
		}else
			return list;
		
		
	}

	
	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg) {
		 
		List<Member> memberList = reg.getMembers();
		
		for(Member member : memberList) {
			
			member.setVaccineRegistration(reg);  // associating each member with VaccineRegistration
		}
		
		return vacRegRepo.save(reg);
	}

	
	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException {
		
		Optional<VaccineRegistration> opt = vacRegRepo.findById(reg.getMobileno());
		
		if(opt.isPresent()) {
			
			return vacRegRepo.save(reg);
			
		}else
			throw new VaccineRegistrationException("No VaccineRegistration data found with details : " + reg);
		
	}

	@Override
	public Boolean deleteVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException {
		 
        Optional<VaccineRegistration> opt = vacRegRepo.findById(reg.getMobileno());
		
		if(opt.isPresent()) {
			
			vacRegRepo.delete(reg);
			return true;
			
		}else
			throw new VaccineRegistrationException("No VaccineRegistration data found with details : " + reg);
		
	}


}

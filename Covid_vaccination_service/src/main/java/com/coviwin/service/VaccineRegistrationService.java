package com.coviwin.service;

import java.util.List;

import com.coviwin.exception.VaccineRegistrationException;
import com.coviwin.model.Member;
import com.coviwin.model.VaccineRegistration;

public interface VaccineRegistrationService {

	public List<VaccineRegistration> getAllVaccineRegistration()throws VaccineRegistrationException;
	
	public VaccineRegistration getVaccineRegistration(Long mobileNo)throws VaccineRegistrationException;
	
	public List<Member> getAllMember(Long mobileNo)throws VaccineRegistrationException;
	
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg);
	
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg)throws VaccineRegistrationException;
	
	public Boolean deleteVaccineRegistration(VaccineRegistration reg)throws VaccineRegistrationException;
	


}

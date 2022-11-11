package com.coviwin.service;

import java.util.List;

import com.coviwin.model.Member;
import com.coviwin.model.VaccineRegistration;

public interface VaccineRegistrationService {

	
	public List<VaccineRegistration> getAllVaccineRegistration();
	public VaccineRegistration getVaccineRegistration(Long mobileNo); //m
	public List<Member> getAllMember(Long mobileNo); // m
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg); //m
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg); 
	public Boolean deleteVaccineRegistration(VaccineRegistration reg); //m 

}

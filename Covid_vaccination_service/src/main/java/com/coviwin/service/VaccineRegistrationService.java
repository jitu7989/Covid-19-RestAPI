package com.coviwin.service;

import java.util.List;

public interface VaccineRegistrationService {

	public List<VaccineRegistration> getAllVaccineRegistration();
	public VaccineRegistration getVaccineRegistration(Long mobileNo);
	public List<Member> getAllMember(Long mobileNo);
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg);
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg);
	public Boolean deleteVaccineRegistration(VaccineRegistration reg);

}

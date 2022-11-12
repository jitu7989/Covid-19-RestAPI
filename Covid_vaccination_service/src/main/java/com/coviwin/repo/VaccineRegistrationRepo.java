package com.coviwin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coviwin.model.Member;
import com.coviwin.model.VaccineRegistration;

public interface VaccineRegistrationRepo extends JpaRepository<VaccineRegistration, Long> {

	@Query("select v.members from VaccineRegistration v where v.mobileno=?1")
	public List<Member> getMembersByMobileNo(Long mobileNo);
	
	
}

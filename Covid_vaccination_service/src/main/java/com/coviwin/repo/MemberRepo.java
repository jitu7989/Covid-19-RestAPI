package com.coviwin.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.IdCard;
import com.coviwin.model.Member;

public interface MemberRepo extends JpaRepository<Member,Integer> {

	public Optional< Member > findByIdCard(IdCard idCardId);
		
	
}

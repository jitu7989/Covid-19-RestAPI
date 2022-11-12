package com.coviwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.IdCard;
import com.coviwin.model.Member;

public interface MemberRepo extends JpaRepository<Member,Integer> {

	public IdCard findByIdCard(int idCardId) ;
		
	
}

package com.coviwin.service;

import com.coviwin.exception.IdCardException;
import com.coviwin.exception.MemberException;
import com.coviwin.model.Member;

public interface MemberService {
	
	// m
		
	public Member getMemberbyId( Integer idCardID ) throws MemberException, IdCardException;
	public Member getMemberByAdharNo(Long adharNo) throws MemberException, IdCardException ;
	public Member getMemberByPanNo( String panNo ) throws MemberException, IdCardException;
	public Member addMember( Integer id,  Member member ) throws MemberException, IdCardException;
	public Member updateMember( Member member ) throws MemberException;
	public Boolean deleteMember( Member member ) throws MemberException;
	
}

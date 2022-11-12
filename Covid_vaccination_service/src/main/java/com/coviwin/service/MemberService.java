package com.coviwin.service;

import com.coviwin.exception.MemberException;
import com.coviwin.model.Member;

public interface MemberService {
	
	// m
		
	public Member getMemberbyId( Integer idCardID ) throws MemberException;
	public Member getMemberByAdharNo(Long adharNo) throws MemberException;
	public Member getMemberByPanNo( String panNo ) throws MemberException;
	public Member addMember( Member member ) throws MemberException;
	public Member updateMember( Member member ) throws MemberException;
	public Boolean deleteMember( Member member ) throws MemberException;
	
}

package com.coviwin.implementation;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.MemberException;
import com.coviwin.model.AdharCard;
import com.coviwin.model.Appointment;
import com.coviwin.model.IdCard;
import com.coviwin.model.Member;
import com.coviwin.model.PanCard;
import com.coviwin.model.VaccineRegistration;
import com.coviwin.repo.MemberRepo;
import com.coviwin.service.MemberService;


@Service 
public class MemberServiceImpl  implements MemberService {

	@Autowired
	MemberRepo memRepo;
	
	@Override
	public Member getMemberbyId(Integer idCardID) throws MemberException {
	 IdCard op  = memRepo.findByIdCard(idCardID);
	 if(op==null)
		 throw new MemberException("Invalid ID card");
	 return op.getMember();
	}

	@Override
	public Member getMemberByAdharNo(Long adharNo) throws MemberException {
	 List<Member> allMembers = memRepo.findAll();
	 Member mem = null;
	   for(Member ele: allMembers)
	   {
		    IdCard idcard = ele.getIdCard();
		    AdharCard adharCard = idcard.getAdharcard();
		    if (adharCard.getAdharNo()==adharNo)
		    {  
		    	mem = ele;
		        break;	
		    }
		   
	   } 
		if(mem==null)
			 throw new MemberException("Invalid Adhar NO.");
		
		return mem;
	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberException {
		
		List<Member> allMembers = memRepo.findAll();
		 Member mem = null;
		   for(Member ele: allMembers)
		   {
			    IdCard idcard = ele.getIdCard();
			    PanCard panCard = idcard.getPancard();
			    if (panCard.getPanNo()==panNo)
			    {  
			    	mem = ele;
			        break;	
			    }
			   
		   } 
			if(mem==null)
				 throw new MemberException("Invalid Pan NO.");
			
			return mem;
	}

	@Override
	public Member addMember(Member member) throws MemberException {
//		
//		Member mem = memRepo.save(member);
//		if(mem==null)
//			throw new MemberException("Unable to save this member");
//		return mem;
		
		memRepo.findById(member.getMemberId()).orElseThrow(() -> new MemberException("Member is already registered with memberId : " + member.getMemberId()));
		
		List<Appointment> appList = member.getAppointments();
		
		for(Appointment app : appList) {
			
			app.setMember(member); // associating each appointment with member 
		}
		
		VaccineRegistration vaccineRegistration =member.getVaccineRegistration();
		vaccineRegistration.getMembers().add(member);  // associating vaccineRegistration with member 
		
		return memRepo.save(member);
	}

	@Override
	public Member updateMember(Member member) throws MemberException {
		
		Optional<Member> op = memRepo.findById(member.getMemberId());
		
		if(op.isEmpty())
			throw new MemberException("Member not found");
	
        List<Appointment> appList = member.getAppointments();
		
		for(Appointment app : appList) {
			
			app.setMember(member); // associating each appointment with member 
		}
		
		VaccineRegistration vaccineRegistration =member.getVaccineRegistration();
		vaccineRegistration.getMembers().add(member);  // associating vaccineRegistration with member 
	   
		return memRepo.save(member);
	}

	@Override
	public Boolean deleteMember(Member member) throws MemberException {
		Optional<Member> op = memRepo.findById(member.getMemberId());
		if(op.isEmpty())
			throw new MemberException("Member not found");
		

	  memRepo.deleteById(member.getMemberId());
	  return true;
	}

}

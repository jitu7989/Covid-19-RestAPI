package com.coviwin.implementation;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.IdCardException;
import com.coviwin.exception.MemberException;
import com.coviwin.model.AdharCard;

import com.coviwin.model.Appointment;
import com.coviwin.model.IdCard;
import com.coviwin.model.Member;
import com.coviwin.model.PanCard;
import com.coviwin.model.VaccineRegistration;
import com.coviwin.repo.IdCardServiceRepo;
import com.coviwin.repo.MemberRepo;
import com.coviwin.service.IdCardService;
import com.coviwin.service.MemberService;


@Service 
public class MemberServiceImpl  implements MemberService {

	@Autowired
	MemberRepo memRepo;
	
	@Autowired
	IdCardServiceRepo idCardServiceRepo;
	
	@Autowired
	IdCardService idCardService;
	
	@Override
	public Member getMemberbyId(Integer idCardID) throws MemberException, IdCardException {
		
		IdCard idCard = idCardServiceRepo.findById(idCardID).orElseThrow( () -> new IdCardException("No id card found with that id")) ;
		
		Member member = memRepo.findByIdCard(idCard).orElseThrow( ()->new MemberException("Member not found") ) ;
		
		return member;
	}

	@Override
	public Member getMemberByAdharNo(Long adharNo) throws MemberException, IdCardException {
		List<IdCard> idCard =  idCardService.getAdharCardByNo(adharNo);
		
		if(idCard==null || idCard.isEmpty()) { 
			throw new MemberException("Member does not exist with that adhar no");
		}
		
		Member member = idCard.get(0).getMember();
		
		if(member==null)
			 throw new MemberException("Member is not created");
		
		return member;
	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberException, IdCardException {
		
		IdCard idCard =  idCardService.getPanCardByNumber(panNo);
		Member member = idCard.getMember();
		if(member==null)
			 throw new MemberException("Member is not created");
		
		return member;
		
	}

	@Override
	public Member addMember( Integer id , Member member) throws MemberException, IdCardException {
		
		if(id == null) throw new MemberException( "Id cannot be null" );
		
		Optional<IdCard> opt = idCardServiceRepo.findById(id);
		
		if( !opt.isPresent() ) throw new IdCardException("No id card found with id:- " + id);
		
		member.setIdCard( opt.get() );
		
		List<Appointment> appList = member.getAppointments();
			
		if(appList != null) {
			for(Appointment app : appList) 
				app.setMember(member); // associating each appointment with member 
		}
		else appList = new ArrayList<>();
			
		VaccineRegistration vaccineRegistration = member.getVaccineRegistration();
			
		if(vaccineRegistration != null)
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

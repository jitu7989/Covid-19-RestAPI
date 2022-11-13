package com.coviwin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.validation.Valid;

import com.coviwin.exception.ApppintmentException;
import com.coviwin.exception.IdCardException;
import com.coviwin.exception.MemberException;
import com.coviwin.exception.VaccinationCenterException;
import com.coviwin.exception.VaccineException;
import com.coviwin.exception.VaccineRegistrationException;
import com.coviwin.model.Appointment;
import com.coviwin.model.IdCard;
import com.coviwin.model.Member;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineRegistration;
import com.coviwin.service.AppointmentService;
import com.coviwin.service.IdCardService;
import com.coviwin.service.MemberService;
import com.coviwin.service.VaccinationCenterService;
import com.coviwin.service.VaccineRegistrationService;
import com.coviwin.service.VaccineService;



@RestController
@RequestMapping("/user")
public class MemberRestController {
	
	@Autowired
	private AppointmentService appointment;
	
	@Autowired
	 private MemberService memberSer;
	 
	@Autowired
	 private  IdCardService idcard;
	
	@Autowired
	private VaccinationCenterService vaccincenterSer;
	
	@Autowired
	private VaccineRegistrationService vaccinRegSer;
	
	@Autowired
	private VaccineService vaccinser;
	 
	// idcard
	@PostMapping("/idCard")
	public ResponseEntity<IdCard> addidCardHandler(@Valid @RequestBody IdCard card) throws IdCardException{
		IdCard addId= idcard.addIdCard(card);
		return new ResponseEntity<IdCard>(addId, HttpStatus.CREATED);
		
	}
	 
	 @GetMapping("/adharCard/{adharNo}")
	 public ResponseEntity<List<IdCard>> getAdharBynoHandler(@PathVariable  Long adharNo) throws IdCardException{
		 List<IdCard> adhardetails = idcard.getAdharCardByNo(adharNo);
		 return new ResponseEntity<List<IdCard>>(adhardetails, HttpStatus.FOUND);
		 
	 }
	 
	 @GetMapping("/panCard/{panNo}")
	 public ResponseEntity<IdCard> getPanByNoHandler(@PathVariable String panNo) throws IdCardException{
		 IdCard pandetails= idcard.getPanCardByNumber(panNo);
		 return new ResponseEntity<IdCard>(pandetails, HttpStatus.FOUND); 
	 }
	 
	 //appointment
	 
		@PostMapping("/appointment")
		public ResponseEntity<Appointment> addAppointmentHandler( @RequestParam Integer memid , @Valid @RequestBody Appointment app) throws ApppintmentException, MemberException {
			Appointment appoint =appointment.addAppointment( memid,  app);
			return new ResponseEntity<Appointment>(appoint, HttpStatus.CREATED);
		}
		
		@GetMapping("/appointment/{bookingID}")
		public ResponseEntity<Appointment> getAppointmentHandler(@PathVariable("bookingID") Long bookingID) throws ApppintmentException {
			return new ResponseEntity<Appointment>(appointment.getAppointment(bookingID),HttpStatus.FOUND);
		}
		
		
		@DeleteMapping("/appointment")
		public ResponseEntity<Boolean> deleteAppoinmentHandler(@Valid @RequestBody Appointment app) throws ApppintmentException{
			 return new ResponseEntity<Boolean>(appointment.deleteAppointment(app), HttpStatus.OK);
		}
		
		
		@PutMapping("/appointment")
		public ResponseEntity<Appointment> updateAppointmentHandler(@Valid @RequestBody Appointment app ) throws ApppintmentException {
			Appointment updateAppointment =appointment.updateAppointment(app);
			return new ResponseEntity<Appointment>(updateAppointment,HttpStatus.OK);
		}
		
		
		// memberservice 
		
		@PostMapping("/member")
		public ResponseEntity<Member> addMemberHandler( @RequestParam Integer id,@Valid @RequestBody Member member) throws MemberException, IdCardException {
			 Member addmember=memberSer.addMember(id,member);
			 return new ResponseEntity<Member>(addmember,HttpStatus.CREATED);
			
		}
		
		@DeleteMapping("/member")
		public ResponseEntity<Boolean> deleteMemberHandler(@Valid @RequestBody Member member) throws MemberException{
			
			return new  ResponseEntity<Boolean>(memberSer.deleteMember(member), HttpStatus.OK);
			
		}
		
		@GetMapping("/memberId/{IdCardId}")
		public ResponseEntity<Member> getMemberByIdHandler(@PathVariable("IdCardId") Integer IdCardId) throws MemberException, IdCardException{
			 Member getmemberbyId=memberSer.getMemberbyId(IdCardId);
			 return new ResponseEntity<Member>(getmemberbyId,HttpStatus.FOUND);
			
		}
		
		@GetMapping("/memberAdhar/{adharNo}")
		public ResponseEntity<Member> getMemberByAdharHandler(@PathVariable("adharNo") Long adharNo) throws MemberException, IdCardException{
			 Member getmemberbyadhar=memberSer.getMemberByAdharNo(adharNo);
			 return new ResponseEntity<Member>(getmemberbyadhar,HttpStatus.FOUND);
			
		}
		
		@GetMapping("/memberPan/{panNo}")
		public ResponseEntity<Member> getMemberByPanHandler(@PathVariable("panNo") String panNo) throws MemberException, IdCardException{
			 Member getmemberbyPan=memberSer.getMemberByPanNo(panNo);
			 return new ResponseEntity<Member>(getmemberbyPan,HttpStatus.FOUND);
			
		}
		
		@PutMapping("/member")
		public ResponseEntity<Member> updateMemberHandler(@Valid @RequestBody Member member ) throws MemberException {
			Member updateMember =memberSer.updateMember(member);
			return new ResponseEntity<Member>(updateMember,HttpStatus.OK);
		}
		
		
		//vaccinCentral
		// list pending
		
		
		@GetMapping("/vaccincenter")
		public ResponseEntity<List<VaccinationCenter>> getAllVacciceInventory() throws VaccinationCenterException {
			
		    List<VaccinationCenter> vInventory =  vaccincenterSer.getAllVaccineCenters();
			
			return new ResponseEntity<List<VaccinationCenter>>( vInventory , HttpStatus.FOUND );
		}
		
		
		@PostMapping("/vaccincenter")
		public ResponseEntity<VaccinationCenter> addVaccineCenterHandler(@Valid @RequestBody  VaccinationCenter vaccincenter) throws VaccinationCenterException {
			VaccinationCenter addVCenter=vaccincenterSer.addVaccineCenter(vaccincenter);
			 return new ResponseEntity<VaccinationCenter>(addVCenter,HttpStatus.CREATED);
					
		}
		
		
		@DeleteMapping("/vaccincenter")
		public ResponseEntity<VaccinationCenter> deletevaccinCenterHandler(@Valid @RequestBody VaccinationCenter vaccincenter) throws VaccinationCenterException {
			
			return new  ResponseEntity<VaccinationCenter>(vaccincenterSer.deleteVaccineCenter(vaccincenter), HttpStatus.OK);
			
		}
		
		@GetMapping("/vaccincenter/{centerid}")
		public ResponseEntity<VaccinationCenter> getVaccineCenterHandler(@PathVariable("centerid") Integer centerid) throws VaccinationCenterException {
			VaccinationCenter getVCenter=vaccincenterSer.getVaccineCenters(centerid);
			 return new ResponseEntity<VaccinationCenter>(getVCenter,HttpStatus.FOUND);
					
		}
		
		@PutMapping("/vaccincenter")
		public ResponseEntity<VaccinationCenter> updateVaccineCenterHandler(@Valid @RequestBody  VaccinationCenter vaccincenter) throws VaccinationCenterException {
			VaccinationCenter addVCenter=vaccincenterSer.updateVaccineCenter(vaccincenter);
			 return new ResponseEntity<VaccinationCenter>(addVCenter,HttpStatus.OK);
					
		}
	
		
		// vaccinRegestration Serervice method
		
		// get vacin reg pending
		
		@DeleteMapping("/vaccineReg")
		public ResponseEntity<Boolean> deleteVaccineReg(@Valid @RequestBody VaccineRegistration vaccinreg) throws VaccineRegistrationException{
			return new ResponseEntity<Boolean>(vaccinRegSer.deleteVaccineRegistration(vaccinreg),HttpStatus.OK);
		}
		
		@GetMapping("/vaccineReg")
		public ResponseEntity<VaccineRegistration> getVaccineReg(@Valid @RequestBody Long mobileNo) throws VaccineRegistrationException{
			VaccineRegistration getvaccinereg=vaccinRegSer.getVaccineRegistration(mobileNo);
			return new ResponseEntity<VaccineRegistration>(getvaccinereg,HttpStatus.FOUND);
			
		}
		
		@PutMapping("/vaccineReg")
		public ResponseEntity<VaccineRegistration> updateVaccineReg(@Valid @RequestBody VaccineRegistration vaccinreg) throws VaccineRegistrationException{
			VaccineRegistration updatevaccinereg=vaccinRegSer.updateVaccineRegistration(vaccinreg);
			return new ResponseEntity<VaccineRegistration>(updatevaccinereg,HttpStatus.OK);
			
		}
		
		@GetMapping("/vaccineReg/{mobileNo}")
		public ResponseEntity<List<Member>> getAllMember (Long mobileNo) throws VaccineRegistrationException{
			
			List<Member> Vaccine=vaccinRegSer.getAllMember(mobileNo);
			
			return new ResponseEntity <List<Member>>(Vaccine,HttpStatus.FOUND);
			
		}
		

		
		
		// vaccineService
		
		@GetMapping("/vaccineserviceByName/{vaccineName}")
		public ResponseEntity< List< Vaccine > > getVaccineByname(@Valid @RequestBody String vaccineName) throws VaccineException{
			List< Vaccine > getvaccinbyname=vaccinser.getVaccineByName(vaccineName);
			return new ResponseEntity<List< Vaccine >>(getvaccinbyname,HttpStatus.FOUND);
		}
		
		@GetMapping("/vaccineserviceById/{vaccineName}")
		public ResponseEntity<Vaccine> getVaccineById( @Valid @RequestBody Integer vaccineId) throws VaccineException{
			Vaccine getvaccinbyid = vaccinser.getVaccineById(vaccineId);
			return new ResponseEntity<Vaccine>(getvaccinbyid,HttpStatus.FOUND);
		}
		
		

}

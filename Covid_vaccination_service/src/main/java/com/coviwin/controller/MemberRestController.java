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
import com.coviwin.exception.LoginException;
import com.coviwin.exception.MemberException;
import com.coviwin.exception.UserException;
import com.coviwin.exception.VaccinationCenterException;
import com.coviwin.exception.VaccineException;
import com.coviwin.exception.VaccineRegistrationException;
import com.coviwin.model.Appointment;
import com.coviwin.model.IdCard;
import com.coviwin.model.Member;
import com.coviwin.model.User;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineRegistration;
import com.coviwin.service.AppointmentService;
import com.coviwin.service.IdCardService;
import com.coviwin.service.MemberService;
import com.coviwin.service.UserLoginService;
import com.coviwin.service.UserService;
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
	
	@Autowired
	private UserService userser;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping("/users")
	public ResponseEntity<User>  saveMember( @RequestBody User user) throws UserException {
	
		User savedUser=  userser.registerUser(user);
		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
	}

	// to update user by passing key
	@PutMapping("/update")
	public ResponseEntity<User> updateMember(@RequestBody User user, @RequestParam(required = false) String key) throws UserException, LoginException {
		 userLoginService.authenthicate(key);
		 User update=userser.updateUser(user, key);
		 return new ResponseEntity<User>(update,HttpStatus.OK);

		
	}
	
	// idcard
	@PostMapping("/idCard")
	public ResponseEntity<IdCard> addidCardHandler(@RequestParam String key ,@Valid @RequestBody IdCard card) throws IdCardException, LoginException{
		userLoginService.authenthicate(key);
		IdCard addId= idcard.addIdCard(card);
		return new ResponseEntity<IdCard>(addId, HttpStatus.CREATED);
		
	}
	 
	 @GetMapping("/adharCard/{adharNo}")
	 public ResponseEntity<List<IdCard>> getAdharBynoHandler(@RequestParam String key ,@PathVariable  Long adharNo) throws IdCardException, LoginException{
		 userLoginService.authenthicate(key);
		 List<IdCard> adhardetails = idcard.getAdharCardByNo(adharNo);
		 return new ResponseEntity<List<IdCard>>(adhardetails, HttpStatus.FOUND);
		 
	 }
	 
	 @GetMapping("/panCard/{panNo}")
	 public ResponseEntity<IdCard> getPanByNoHandler(@RequestParam String key ,@PathVariable String panNo) throws IdCardException, LoginException{
		 userLoginService.authenthicate(key);
		 IdCard pandetails= idcard.getPanCardByNumber(panNo);
		 return new ResponseEntity<IdCard>(pandetails, HttpStatus.FOUND); 
	 }
	 
	 //appointment
	 
		@PostMapping("/appointment")
		public ResponseEntity<Appointment> addAppointmentHandler( @RequestParam String key ,@RequestParam Integer memid , @Valid @RequestBody Appointment app) throws ApppintmentException, MemberException, LoginException {
			userLoginService.authenthicate(key);
			Appointment appoint =appointment.addAppointment( memid,  app);
			return new ResponseEntity<Appointment>(appoint, HttpStatus.CREATED);
		}
		
		@GetMapping("/appointment/{bookingID}")
		public ResponseEntity<Appointment> getAppointmentHandler(@RequestParam String key ,@PathVariable("bookingID") Long bookingID) throws ApppintmentException, LoginException {
			userLoginService.authenthicate(key);
			return new ResponseEntity<Appointment>(appointment.getAppointment(bookingID),HttpStatus.FOUND);
		}
		
		
		@DeleteMapping("/appointment")
		public ResponseEntity<Boolean> deleteAppoinmentHandler(@RequestParam String key ,@Valid @RequestBody Appointment app) throws ApppintmentException, LoginException{
			userLoginService.authenthicate(key); 
			return new ResponseEntity<Boolean>(appointment.deleteAppointment(app), HttpStatus.OK);
		}
		
		
		@PutMapping("/appointment")
		public ResponseEntity<Appointment> updateAppointmentHandler(@RequestParam String key ,@Valid @RequestBody Appointment app ) throws ApppintmentException, LoginException {
			userLoginService.authenthicate(key);
			Appointment updateAppointment =appointment.updateAppointment(app);
			return new ResponseEntity<Appointment>(updateAppointment,HttpStatus.OK);
		}
		
		
		// memberservice 
		
		@PostMapping("/member")
		public ResponseEntity<Member> addMemberHandler( @RequestParam String key ,@RequestParam Integer id,@Valid @RequestBody Member member) throws MemberException, IdCardException, LoginException {
			userLoginService.authenthicate(key); 
			Member addmember=memberSer.addMember(id,member);
			 return new ResponseEntity<Member>(addmember,HttpStatus.CREATED);
			
		}
		
		@DeleteMapping("/member")
		public ResponseEntity<Boolean> deleteMemberHandler(@RequestParam String key ,@Valid @RequestBody Member member) throws MemberException, LoginException{
			userLoginService.authenthicate(key);
			return new  ResponseEntity<Boolean>(memberSer.deleteMember(member), HttpStatus.OK);
			
		}
		
		@GetMapping("/memberId/{IdCardId}")
		public ResponseEntity<Member> getMemberByIdHandler(@RequestParam String key ,@PathVariable("IdCardId") Integer IdCardId) throws MemberException, IdCardException, LoginException{
			userLoginService.authenthicate(key); 
			Member getmemberbyId=memberSer.getMemberbyId(IdCardId);
			 return new ResponseEntity<Member>(getmemberbyId,HttpStatus.FOUND);
			
		}
		
		@GetMapping("/memberAdhar/{adharNo}")
		public ResponseEntity<Member> getMemberByAdharHandler(@RequestParam String key ,@PathVariable("adharNo") Long adharNo) throws MemberException, IdCardException, LoginException{
			 userLoginService.authenthicate(key);
			 
			 Member getmemberbyadhar=memberSer.getMemberByAdharNo(adharNo);
			 return new ResponseEntity<Member>(getmemberbyadhar,HttpStatus.FOUND);
			
		}
		
		@GetMapping("/memberPan/{panNo}")
		public ResponseEntity<Member> getMemberByPanHandler(@RequestParam String key ,@PathVariable("panNo") String panNo) throws MemberException, IdCardException, LoginException{
			 
			 userLoginService.authenthicate(key);
			 Member getmemberbyPan=memberSer.getMemberByPanNo(panNo);
			 return new ResponseEntity<Member>(getmemberbyPan,HttpStatus.FOUND);
			
		}
		
		@PutMapping("/member")
		public ResponseEntity<Member> updateMemberHandler(@RequestParam String key ,@Valid @RequestBody Member member ) throws MemberException , LoginException{
			
			userLoginService.authenthicate(key);
			Member updateMember =memberSer.updateMember(member);
			return new ResponseEntity<Member>(updateMember,HttpStatus.OK);
		}
		
		
		//VaccinationCenter
	
		
		@GetMapping("/vaccincenter")
		public ResponseEntity<List<VaccinationCenter>> getAllVaccinationCenter(@RequestParam String key ) throws VaccinationCenterException , LoginException{
			userLoginService.authenthicate(key);
		    List<VaccinationCenter> vInventory =  vaccincenterSer.getAllVaccineCenters();
			
			return new ResponseEntity<List<VaccinationCenter>>( vInventory , HttpStatus.FOUND );
		}
		

		
		@DeleteMapping("/vaccineReg")
		public ResponseEntity<Boolean> deleteVaccineReg(@RequestParam String key ,@Valid @RequestBody VaccineRegistration vaccinreg) throws VaccineRegistrationException, LoginException{
			userLoginService.authenthicate(key);
			
			return new ResponseEntity<Boolean>(vaccinRegSer.deleteVaccineRegistration(vaccinreg),HttpStatus.OK);
		}
		
		@PostMapping("/vaccineReg")
		public ResponseEntity<VaccineRegistration> getVaccineReg(@RequestParam String key ,@Valid @RequestBody Long mobileNo) throws VaccineRegistrationException, LoginException{
			userLoginService.authenthicate(key);
			VaccineRegistration getvaccinereg=vaccinRegSer.getVaccineRegistration(mobileNo);
			return new ResponseEntity<VaccineRegistration>(getvaccinereg,HttpStatus.FOUND);
			
		}
		
		@PutMapping("/vaccineReg")
		public ResponseEntity<VaccineRegistration> updateVaccineReg(@RequestParam String key ,@Valid @RequestBody VaccineRegistration vaccinreg) throws VaccineRegistrationException, LoginException{
			
			userLoginService.authenthicate(key);
			VaccineRegistration updatevaccinereg=vaccinRegSer.updateVaccineRegistration(vaccinreg);
			return new ResponseEntity<VaccineRegistration>(updatevaccinereg,HttpStatus.OK);
			
		}
		
		
		
		// vaccineService
		
		@GetMapping("/vaccineserviceByName/{vaccineName}")
		public ResponseEntity< List< Vaccine > > getVaccineByname(@RequestParam String key ,@Valid @RequestBody String vaccineName) throws VaccineException, LoginException{
			userLoginService.authenthicate(key);
			
			List< Vaccine > getvaccinbyname=vaccinser.getVaccineByName(vaccineName);
			return new ResponseEntity<List< Vaccine >>(getvaccinbyname,HttpStatus.FOUND);
		}


		
		@GetMapping("/vaccineserviceById/{vaccineName}")
		public ResponseEntity<Vaccine> getVaccineById( @RequestParam String key ,@Valid @RequestBody Integer vaccineId) throws VaccineException, LoginException{
			userLoginService.authenthicate(key);
			
			Vaccine getvaccinbyid = vaccinser.getVaccineById(vaccineId);
			return new ResponseEntity<Vaccine>(getvaccinbyid,HttpStatus.FOUND);
		}

		
		

}

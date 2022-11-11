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

import com.coviwin.exception.IdCardException;
import com.coviwin.exception.MemberException;
import com.coviwin.model.Appointment;
import com.coviwin.model.IdCard;
import com.coviwin.model.Member;
import com.coviwin.service.AppointmentService;
import com.coviwin.service.IdCardService;
import com.coviwin.service.MemberService;


@RestController
@RequestMapping("/CowinUser")

public class UserController {
	
	@Autowired
	private AppointmentService appointment;
	
	@Autowired
	 private MemberService memberSer;
	 
	@Autowired
	 private  IdCardService idcard;
	 
	// idcard
	@PostMapping("/idCard")
	public ResponseEntity<IdCard> addidCardHandler(@RequestBody IdCard card){
		IdCard addId= idcard.addIdCard(card);
		return new ResponseEntity<IdCard>(addId, HttpStatus.CREATED);
		
	}
	 
	 @GetMapping("/adharCard/{adharNo}")
	 public ResponseEntity<IdCard> getAdharBynoHandler(@PathVariable  Long adharNo) throws IdCardException{
		 IdCard adhardetails = idcard.getAdharCardByNo(adharNo);
		 return new ResponseEntity<IdCard>(adhardetails, HttpStatus.FOUND);
		 
	 }
	 
	 @GetMapping("/panCard/{panNo}")
	 public ResponseEntity<IdCard> getPanByNoHandler(@PathVariable String panNo) throws IdCardException{
		 IdCard pandetails= idcard.getPanCardByNumber(panNo);
		 return new ResponseEntity<IdCard>(pandetails, HttpStatus.FOUND); 
	 }
	 
	 //appointment
	 //delete appointment is not imple
	 
		@PostMapping("/appointment")
		public ResponseEntity<Appointment> addAppointmentHandler(@RequestBody Appointment app) {
			Appointment appoint =appointment.addAppointment(app);
			return new ResponseEntity<Appointment>(appoint, HttpStatus.CREATED);
		}
		
		@GetMapping("/appointment/{bookingID}")
		public ResponseEntity<Appointment> getAppointmentHandler(@PathVariable("bookingID") Long bookingID) {
			return new ResponseEntity<Appointment>(appointment.getAppointment(bookingID),HttpStatus.FOUND);
		}
		
		
		@DeleteMapping("/appointment")
		public ResponseEntity<Boolean> deleteAppoinmentHandler(@RequestBody Appointment app){
			 return new ResponseEntity<Boolean>(appointment.deleteAppointment(app), HttpStatus.OK);
		}
		
		
		@PutMapping("/appointment")
		public ResponseEntity<Appointment> updateAppointmentHandler(@RequestBody Appointment app ) {
			Appointment updateAppointment =appointment.updateAppointment(app);
			return new ResponseEntity<Appointment>(updateAppointment,HttpStatus.OK);
		}
		
		
		// memberservice 
		
		@PostMapping("/member")
		public ResponseEntity<Member> addMemberHandler(@RequestBody Member member) throws MemberException {
			 Member addmember=memberSer.addMember(member);
			 return new ResponseEntity<Member>(addmember,HttpStatus.ACCEPTED);
			
		}
		
		
		
		
		

}

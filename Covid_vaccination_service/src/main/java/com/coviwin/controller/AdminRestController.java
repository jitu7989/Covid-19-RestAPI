package com.coviwin.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

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

import com.coviwin.exception.ApppintmentException;
import com.coviwin.exception.IdCardException;
import com.coviwin.exception.MemberException;
import com.coviwin.exception.VaccinationCenterException;
import com.coviwin.exception.VaccineException;
import com.coviwin.exception.VaccineInventoryException;
import com.coviwin.model.Appointment;
import com.coviwin.model.IdCard;
import com.coviwin.model.Member;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineCount;
import com.coviwin.model.VaccineInventory;
import com.coviwin.service.AppointmentService;
import com.coviwin.service.IdCardService;
import com.coviwin.service.MemberService;
import com.coviwin.service.VaccinationCenterService;
import com.coviwin.service.VaccineInventoryService;
import com.coviwin.service.VaccineService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	@Autowired
	public VaccinationCenterService vaccinationCenter;
	
	@Autowired
	public IdCardService idCardService;
	
	@Autowired
	public VaccineInventoryService vaccineInventoryService;
	
	@Autowired
	public VaccineService vService; 
	
	@Autowired
	public MemberService memberService;
	
	@Autowired
	public AppointmentService appointmentService;
	
//	Vaccination center Service method
	
	@GetMapping(value = "/centers")
	public ResponseEntity<List<VaccinationCenter>> allVaccinationCenter() throws VaccinationCenterException{
		
		List<VaccinationCenter> vcLS = vaccinationCenter.getAllVaccineCenters();
		
		return new ResponseEntity<List<VaccinationCenter>>( vcLS , HttpStatus.FOUND );
		
	}
	
	@GetMapping(value="/center")
	public ResponseEntity<VaccinationCenter> getVaccineCenter(@RequestParam Integer id) throws VaccinationCenterException{
		
		VaccinationCenter vcLS = vaccinationCenter.getVaccineCenters(id);
		return  new ResponseEntity<VaccinationCenter>( vcLS , HttpStatus.FOUND );
		
	}
	
	@PostMapping(value="/center")
	public ResponseEntity< VaccinationCenter> addVaccineCenter( @Valid @RequestBody VaccinationCenter vaccinationCenter ) throws VaccinationCenterException{
		
		
		VaccinationCenter vCenter = this.vaccinationCenter.addVaccineCenter(vaccinationCenter);
		
		return new ResponseEntity<>(vCenter , HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/center")
	public ResponseEntity< VaccinationCenter > updateCenter( @Valid @RequestBody VaccinationCenter vc ) throws VaccinationCenterException {
		
		VaccinationCenter retvc =  vaccinationCenter.updateVaccineCenter(vc);
		
		return new ResponseEntity<VaccinationCenter>( retvc , HttpStatus.ACCEPTED );
		
	}
	
	@DeleteMapping(value = "/center")
	public ResponseEntity<VaccinationCenter> deleteCenter( @Valid @RequestBody VaccinationCenter center ) throws VaccinationCenterException{
		
		VaccinationCenter vCenter =  vaccinationCenter.deleteVaccineCenter( center );

		return new ResponseEntity<VaccinationCenter>( vCenter , HttpStatus.ACCEPTED );
	}
	
//	Id Card Service method
	
	@GetMapping("/getId")
	public ResponseEntity< IdCard > getIdCardByAdhar( @Valid
													  @Digits(integer = 12,fraction = 12,message = "Length must be 12")
													  @RequestParam(value = "adhar") Long adharLong ) throws IdCardException{
		
	 	IdCard idCard = idCardService.getAdharCardByNo(adharLong);
		
		return new ResponseEntity<>( idCard ,  HttpStatus.FOUND );
	}
	
	@GetMapping("/getId")
	public ResponseEntity< IdCard > getIdCardByPan( @RequestParam String pan ) throws IdCardException{
		
	 	IdCard idCard = idCardService.getPanCardByNumber( pan );
		
		return new ResponseEntity<>( idCard ,  HttpStatus.FOUND );
	}
	
	@PostMapping("/addId")
	public ResponseEntity< IdCard > addId( @Valid @RequestBody IdCard id ) throws IdCardException{
		
	 	IdCard idCard = idCardService.addIdCard( id );
		
		return new ResponseEntity<>( idCard ,  HttpStatus.FOUND );
		
	}
	
//	Vaccine Inventory Service Method
	
	@GetMapping("/vaccineInventories")
	public ResponseEntity<List< VaccineInventory >> getAllVacciceInventory() throws VaccineInventoryException {
		
		List< VaccineInventory > vInventory =  vaccineInventoryService.allVaccineInventory();
		
		return new ResponseEntity<List<VaccineInventory>>( vInventory , HttpStatus.FOUND );
	}
	
	@GetMapping("/vaccineInventories/vaccine")

	public ResponseEntity< List< VaccineInventory > > getVaccineInventoryByVaccine( @Valid @RequestBody Vaccine vaccine ) throws VaccineInventoryException{
		 List<VaccineInventory> vi = vaccineInventoryService.getVaccineInventoryByVaccine(vaccine);
		 return new ResponseEntity<List<VaccineInventory>>(  vi , HttpStatus.FOUND );
	}
	
	
	@DeleteMapping("/vaccineInventory/vi")
	public ResponseEntity<Boolean> deleteVaccineInventory( @PathVariable("vi") Integer arg ) throws VaccineInventoryException{
		
		Boolean fBoolean =  vaccineInventoryService.deleteVaccineInventory( arg );
		
		return new ResponseEntity<Boolean>( fBoolean ,HttpStatus.ACCEPTED );
	} 
	
	@PostMapping("/vaccineInventory/{centerId}")
	public ResponseEntity<VaccineInventory> addInventoryByCenter( @Valid @RequestBody VaccineInventory vInventory, @PathVariable("centerId") Integer centerId) throws VaccineInventoryException {
		
		VaccineInventory vaccineInventory =  vaccineInventoryService.addVaccineInventoryByCenter( centerId , vInventory);
		
		
		return new ResponseEntity<VaccineInventory>( vaccineInventory , HttpStatus.ACCEPTED );
	}
	
	@PostMapping("/vaccineInventory")
	public ResponseEntity< VaccineInventory > updateVaccineCount( @Valid @RequestBody VaccineInventory vi , VaccineCount vc ) throws VaccineInventoryException{
		
		VaccineInventory vInventory = vaccineInventoryService.addVaccineCount( vi , vc );
		
		
		return new ResponseEntity<VaccineInventory>( vInventory , HttpStatus.ACCEPTED );
		
	}
	@PutMapping("/vaccineInventory")
	public ResponseEntity< VaccineInventory > updateVaccineInventory( @Valid @RequestParam Integer id, @RequestBody VaccineInventory vi ) throws VaccineInventoryException{
		
		VaccineInventory vaccineInventory = vaccineInventoryService.updateVaccineInventory(id, vi);
		
		return new ResponseEntity<VaccineInventory>( vaccineInventory , HttpStatus.ACCEPTED );
	}
	
	
	
//	Vaccine Service Method
	
	@PostMapping("/vaccine")
	public ResponseEntity<Vaccine> addVaccine( @Valid @RequestBody Vaccine vaccine) throws VaccineException{
		
		 Vaccine v = vService.addVaccine(vaccine);
		 
		 return new ResponseEntity<>( v , HttpStatus.CREATED );
		 
	}
	
	@GetMapping("/vaccines")
	public ResponseEntity<List<Vaccine>> getAllVaccine() throws VaccineException {
		
		List<Vaccine> vaccine =  vService.allVaccine();
		

		return new ResponseEntity<List<Vaccine>>( vaccine ,  HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/vaccine")
	public ResponseEntity< Boolean > deleteVaccine( @Valid @RequestBody Vaccine v ) throws VaccineException{
		
		Boolean boolean1 = vService.deleteVaccine( v );
		
		
		return new ResponseEntity<Boolean>( boolean1 , HttpStatus.ACCEPTED );
	} 
	
	@GetMapping("/vaccine/{vaccine}")
	public ResponseEntity< Vaccine > getVaccineById( @PathVariable("vaccine") Integer id ) throws VaccineException{
		
		Vaccine vaccine =  vService.getVaccineById( id );
		
		return new ResponseEntity<Vaccine>( vaccine  , HttpStatus.FOUND );
	}
	
	@GetMapping("/vaccine")
	public ResponseEntity< List<Vaccine>  > getVaccineByName( @RequestParam String name) throws VaccineException{
		
		List<Vaccine> vaccine = vService.getVaccineByName(name);
		
		return new ResponseEntity<List<Vaccine> >( vaccine , HttpStatus.ACCEPTED);
	}
	
	
//	Member Service
	
	@GetMapping( "/member" )
	public ResponseEntity< Member > getMemberById( @RequestParam Integer id ) throws MemberException{
		
		Member member = memberService.getMemberbyId(id);
		
		
		
		return new ResponseEntity<Member>( member , HttpStatus.FOUND );
	}
	

	
	@PostMapping("/member")
	public ResponseEntity< Member > addMember( @Valid @RequestBody  Member member ) throws MemberException{
		
		Member member2 = memberService.addMember(member);
		
		return new  ResponseEntity<>( member2 , HttpStatus.ACCEPTED );
		
	}
	
	@DeleteMapping("/member")
	public ResponseEntity< Boolean > deleteMember( @Valid @RequestBody Member member ) throws MemberException{
		
		Boolean member2 =  memberService.deleteMember(member);
		
		return new ResponseEntity<Boolean>( member2 , HttpStatus.ACCEPTED );
	}
	
	
	@GetMapping("/member")
	public ResponseEntity< Member > getMemberByPanNo( @RequestParam  String p ) throws MemberException{
		
		Member member = memberService.getMemberByPanNo(p);
			
		return new ResponseEntity<Member>( member , HttpStatus.ACCEPTED );
	}
	
	@PutMapping("/member")
	public ResponseEntity< Member > updateMember( @Valid @RequestBody Member member) throws MemberException{
		
		Member member2 = memberService.updateMember(member);
		return new ResponseEntity<Member>( member2 , HttpStatus.ACCEPTED );
	}
	
	@GetMapping( "/member" )
	public ResponseEntity< Member > getMemberById( @RequestParam Long a ) throws MemberException{
		
		Member member = memberService.getMemberByAdharNo(a);
				
		return new ResponseEntity<Member>( member , HttpStatus.FOUND );
	}
	
//	AppointmentService Method

	@GetMapping("/appointments")
	public ResponseEntity< List<Appointment>> getAllAppointment() throws ApppintmentException{
		
		List<Appointment> ls = appointmentService.allAppointment();
		return new ResponseEntity<List<Appointment>>( ls , HttpStatus.FOUND );
	}
	
	@DeleteMapping("/appointment")
	public ResponseEntity<Boolean> deleteAppointment( @Valid @RequestBody Appointment appointment ) throws ApppintmentException{
		
		Boolean appointment2 =  appointmentService.deleteAppointment(appointment);
		return new ResponseEntity<Boolean>(appointment2 , HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/appointment")
	public ResponseEntity< Appointment > getAppointment ( @RequestParam Long bid ) throws ApppintmentException{
		
		Appointment appointment =  appointmentService.getAppointment(bid);
		
		return new ResponseEntity<Appointment>(appointment,HttpStatus.FOUND);
	}
	
	@PutMapping("appointment")
	public ResponseEntity<Appointment> updateAppointment( @Valid @RequestBody Appointment appointment ) throws ApppintmentException{
		
		Appointment app = appointmentService.updateAppointment(appointment);
		return new ResponseEntity< Appointment>(app,HttpStatus.ACCEPTED);
	}
	
	
}

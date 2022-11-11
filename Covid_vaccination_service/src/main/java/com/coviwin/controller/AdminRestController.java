package com.coviwin.controller;

import java.security.PublicKey;
import java.util.List;

import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

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
import com.coviwin.exception.VaccineInventoryException;
import com.coviwin.model.IdCard;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.Vaccine;
import com.coviwin.model.VaccineInventory;
import com.coviwin.service.IdCardService;
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
	
//	Vaccination center Service method
	
	@GetMapping(value = "/centers")
	public ResponseEntity<List<VaccinationCenter>> allVaccinationCenter(){
		
		List<VaccinationCenter> vcLS = vaccinationCenter.getAllVaccineCenters();
		
		return new ResponseEntity<List<VaccinationCenter>>( vcLS , HttpStatus.FOUND );
		
	}
	
	@GetMapping(value="/center")
	public ResponseEntity<VaccinationCenter> getVaccineCenter(@RequestParam Integer id){
		
		VaccinationCenter vcLS = vaccinationCenter.getVaccineCenters(id);
		return  new ResponseEntity<VaccinationCenter>( vcLS , HttpStatus.FOUND );
		
	}
	
	@PostMapping(value="/center")
	public ResponseEntity< VaccinationCenter> addVaccineCenter( @RequestBody VaccinationCenter vaccinationCenter ){
		
		VaccinationCenter vCenter = this.vaccinationCenter.addVaccineCenter(vaccinationCenter);
		
		return new ResponseEntity<>(vCenter , HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/center")
	public ResponseEntity< VaccinationCenter > updateCenter( @RequestBody VaccinationCenter vc ) {
		
		VaccinationCenter retvc =  vaccinationCenter.updateVaccineCenter(vc);
		
		return new ResponseEntity<VaccinationCenter>( retvc , HttpStatus.ACCEPTED );
		
	}
	
	@DeleteMapping(value = "/center")
	public ResponseEntity<VaccinationCenter> deleteCenter( @RequestBody VaccinationCenter center ){
		
		VaccinationCenter vCenter =  vaccinationCenter.deleteVaccineCenter( center );

		return new ResponseEntity<VaccinationCenter>( vCenter , HttpStatus.ACCEPTED );
	}
	
//	Id Card Service method
	
	@GetMapping("/getId")
	public ResponseEntity< IdCard > getIdCardByAdhar( @RequestParam(value = "adhar") Long adharLong ) throws IdCardException{
		
	 	IdCard idCard = idCardService.getAdharCardByNo(adharLong);
		
		return new ResponseEntity<>( idCard ,  HttpStatus.FOUND );
	}
	
	@GetMapping("/getId")
	public ResponseEntity< IdCard > getIdCardByPan( @RequestParam String pan ) throws IdCardException{
		
	 	IdCard idCard = idCardService.getPanCardByNumber( pan );
		
		return new ResponseEntity<>( idCard ,  HttpStatus.FOUND );
	}
	
	@PostMapping("/addId")
	public ResponseEntity< IdCard > addId( @RequestBody IdCard id ) throws IdCardException{
		
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
	public ResponseEntity< List< VaccineInventory > > getVaccineInventoryByVaccine( @RequestBody Vaccine vaccine ) throws VaccineInventoryException{
		
		 List<VaccineInventory> vi = vaccineInventoryService.getVaccineInventoryByVaccine(vaccine);
		 return new ResponseEntity<List<VaccineInventory>>(  vi , HttpStatus.FOUND );
	}
	
	
	@DeleteMapping("/vaccineInventory/vi")
	public ResponseEntity<Boolean> deleteVaccineInventory( @PathVariable("vi") Integer arg ) throws VaccineInventoryException{
		
		Boolean fBoolean =  vaccineInventoryService.deleteVaccineInventory( arg );
		
		return new ResponseEntity<Boolean>( fBoolean ,HttpStatus.ACCEPTED );
	} 
	
	
//	Vaccine Service Method
	
	@PostMapping("/vaccine")
	public ResponseEntity<Vaccine> addVaccine(@RequestBody Vaccine vaccine){
		
		 Vaccine v = vService.addVaccine(vaccine);
		 
		 return new ResponseEntity<>( v , HttpStatus.CREATED );
		 
	}
	
	
	
}

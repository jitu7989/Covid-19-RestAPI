package com.coviwin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coviwin.exception.AdminException;
import com.coviwin.model.Admin;
import com.coviwin.service.AdminService;



@RestController
public class AdminRestController {
	@Autowired
	private AdminService cService;
	
	@PostMapping("/admins")
	public ResponseEntity<Admin> saveCustomer(@RequestBody Admin customer) throws AdminException{
		Admin savedCustomer=  cService.registerCustomer(customer);
		
		return new  ResponseEntity<Admin>(savedCustomer,HttpStatus.CREATED);
	}
	
	@PutMapping("/admins")
	public ResponseEntity<Admin> updateCustomer(@RequestBody Admin customer,@RequestParam(required = false) String key) throws AdminException{
	 Admin updateCustomer=	cService.updateCustomer(customer, key);
	 return new ResponseEntity<Admin>(updateCustomer,HttpStatus.OK);
	}

}

package com.coviwin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coviwin.exception.LoginException;
import com.coviwin.model.LoginDTO;
import com.coviwin.model.UserDTO;
import com.coviwin.service.AdminLoginService;
import com.coviwin.service.UserLoginService;


@RestController
public class LoginController {
	@Autowired
	private AdminLoginService lservice;
	
	@Autowired
	private UserLoginService userLogInService;
	
	@PostMapping("/adminlogin")
	public ResponseEntity<String> logInCustomer(@RequestBody LoginDTO dto) throws LoginException{
		
	   String result=	lservice.loginAccount(dto);
	   
	   return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PostMapping("/adminlogout")
	public String logOutCustomer(@RequestParam(required  = false) String key) throws LoginException{
		return lservice.logoutAccount(key);
	}
	
	
	// for user login
	@PostMapping("/userlogin")
	public ResponseEntity<String>  logInUser(@RequestBody UserDTO userDTO) throws LoginException {
		String result= userLogInService.logIntoAccount(userDTO);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}

	// for user logout
	@PostMapping("/userlogout")
	public String logOutUser(@RequestParam(required = false) String key) throws LoginException {
		return userLogInService.logOutAccount(key);
	}
	

}

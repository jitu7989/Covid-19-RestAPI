package com.coviwin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coviwin.exception.UserException;
import com.coviwin.model.User;
import com.coviwin.service.UserService;



@RestController
public class UserContoller {
	
	@Autowired
	private UserService userser;
	
	
	// to register user
		@PostMapping("/users")
		public ResponseEntity<User>  saveUser(@RequestBody User user) throws UserException {
			User savedUser=  userser.registerUser(user);
			return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
		}

		// to update user by passing key
		@PutMapping("/update")
		public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam(required = false) String key) throws UserException {
		 User update=userser.updateUser(user, key);
		 return new ResponseEntity<User>(update,HttpStatus.OK);

			
		}

}

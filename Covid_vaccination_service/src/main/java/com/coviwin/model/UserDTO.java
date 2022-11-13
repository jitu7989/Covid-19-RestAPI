package com.coviwin.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDTO {

	@NotNull(message = "User Id can't be null...")
	@NotBlank(message = "User Id cannot be blank.")
	@NotEmpty(message = "User Id cannot be empty.")
	//@NotNull(message = "Mobile is mandatory")
	private String mobileNo;
	
//	@NotNull(message = "Password is mandatory")
	private String password;
	
}

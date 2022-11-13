package com.coviwin.service;

import com.coviwin.exception.LoginException;
import com.coviwin.model.UserDTO;

public interface UserLoginService {

	public String logIntoAccount(UserDTO userDTO) throws LoginException;
	
	public String logOutAccount(String key)throws LoginException;
}

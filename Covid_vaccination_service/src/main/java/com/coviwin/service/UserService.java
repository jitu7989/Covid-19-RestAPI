package com.coviwin.service;

import com.coviwin.exception.UserException;
import com.coviwin.model.User;

public interface UserService {

	public User registerUser(User user)throws UserException;
	
	public User updateUser(User user, String key)throws UserException;
}

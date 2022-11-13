package com.coviwin.service;

import com.coviwin.exception.LoginException;
import com.coviwin.model.LoginDTO;

public interface AdminLoginService {
	public String loginAccount(LoginDTO dto)throws LoginException;
	public String logoutAccount(String key)throws LoginException;
	public  Boolean authenthicate( String key ) throws LoginException;
	

}
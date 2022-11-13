package com.coviwin.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.LoginException;
import com.coviwin.model.CurrentUserSession;
import com.coviwin.model.User;
import com.coviwin.model.UserDTO;
import com.coviwin.repo.UserDao;
import com.coviwin.repo.UserSessionDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLogInServiceImpl implements UserLoginService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public String logIntoAccount(UserDTO userDTO) throws LoginException {
User opt= userDao.findByMobileNo(userDTO.getMobileNo());
		
		if(opt==null) {
			throw new LoginException("Please Enter valid mobile Number");
		}
		
		
		Optional<CurrentUserSession>  currUseropt1= userSessionDAO.findById(opt.getId());
		
		if(currUseropt1.isPresent()) {
			throw new LoginException("User already logedin on this number");
		}
		
		if(opt.getPassword().equals(userDTO.getPassword())) {
			
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession(opt.getId(),key,LocalDateTime.now());
			
			userSessionDAO.save(currentUserSession);
			
			return currentUserSession.toString();
		}
		else {
			throw new LoginException("Please Enter a Valid Password");
		}
	}

	@Override
	public String logOutAccount(String key) throws LoginException {
CurrentUserSession currUserOpt=userSessionDAO.findByUuid(key);
		
		if(currUserOpt==null) {
			throw new LoginException("User not Loged In with this Number");
		}
		userSessionDAO.delete(currUserOpt);
		return "Logged Out";
	}
	
	
}

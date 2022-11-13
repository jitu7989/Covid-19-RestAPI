package com.coviwin.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.UserException;
import com.coviwin.model.CurrentUserSession;
import com.coviwin.model.User;
import com.coviwin.repo.UserDao;
import com.coviwin.repo.UserSessionDAO;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Override
	public User registerUser(User user) throws UserException {
	  User existingUs=	userDao.findByMobileNo(user.getMobileNo());
	  if(existingUs!=null) {
		  throw new UserException("User already exist");
	  }
	  else {
		  return userDao.save(user);
	  }
		
		
	}

	@Override
	public User updateUser(User user, String key) throws UserException {
	  CurrentUserSession loginUser=	userSessionDAO.findByUuid(key);
	  if(loginUser==null) {
		  throw new UserException("Please provide Valid key to update user Details");
	  }
	  else {
		  if(user.getId()==loginUser.getUserId()) {
			  return userDao.save(user);
		  }
		  else {
			  throw new UserException("Invalid User Details,Please login First");
		  }
	  }
	}

}

package com.coviwin.implementation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.LoginException;
import com.coviwin.model.Admin;
import com.coviwin.model.CurrentAdminSession;
import com.coviwin.model.LoginDTO;
import com.coviwin.repo.AdminDao;
import com.coviwin.repo.CurrentAdminSessionDao;
import com.coviwin.service.AdminLoginService;

import net.bytebuddy.utility.RandomString;
@Service
public class AdminLoginServiceImpl implements AdminLoginService{
	
	@Autowired
	private AdminDao customerdao;
	
	@Autowired
	private CurrentAdminSessionDao currentdao;

	
	
	@Override
	public String loginAccount(LoginDTO dto) throws LoginException {
		
		Admin existingCustomer = customerdao.findByMobileNo(dto.getMobileNo());
		
		if(existingCustomer==null) {
			throw new LoginException("Please Enter valid mobile Number");
		}
		
		Optional<CurrentAdminSession> validCustomerSession= currentdao.findById(existingCustomer.getCustomerId());
		
		if(validCustomerSession.isPresent()) {
			throw new LoginException("User already logedin on this number ");
		}
		
		
		if(existingCustomer.getPassword().equals(dto.getPassword())) {
			
			String key = RandomString.make(6); //make unique key

			CurrentAdminSession currentUserSession=new CurrentAdminSession(existingCustomer.getCustomerId(),key,LocalDateTime.now());
			
			currentdao.save(currentUserSession);
			return currentUserSession.toString();
			
		}
		else {
			throw new LoginException("Please Enter a valid password");
		}
	}

	@Override
	public String logoutAccount(String key) throws LoginException {
		CurrentAdminSession validCustomerSession = currentdao.findByUuid(key);
		if(validCustomerSession==null) {
			throw new LoginException("User not Loged In with this Number");
		}
		currentdao.delete(validCustomerSession);
		return "Logged Out";
	}

	
	@Override
	public Boolean authenthicate(String key) throws LoginException {
		
		CurrentAdminSession cdSession = currentdao.findByUuid(key);
		
		if(cdSession==null) throw new LoginException("Admin not active");
		
		return true;
	}

}

package com.coviwin.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.AdminException;
import com.coviwin.model.Admin;
import com.coviwin.model.CurrentAdminSession;
import com.coviwin.repo.AdminDao;
import com.coviwin.repo.CurrentAdminSessionDao;
import com.coviwin.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao customerDao;
	
	@Autowired
	private CurrentAdminSessionDao currentDao;

	@Override
	public Admin registerCustomer(Admin customer) throws AdminException {
		Admin existingCus=  customerDao.findByMobileNo(customer.getMobileNo());
		if(existingCus !=null) {
			throw new AdminException("Customer already exist");
		}
		else {
			
			return customerDao.save(customer);
		}
	}

	@Override
	public Admin updateCustomer(Admin customer, String key) throws AdminException {
		  CurrentAdminSession logedinUser =  currentDao.findByUuid(key);
		  
		  if(logedinUser ==null) {
			  throw new AdminException("Please provide Valid key to update customer Details");
			  
		  }
		  else {
			  if(customer.getCustomerId()==logedinUser.getUserId()) {
				return  customerDao.save(customer);
			  }
			  else {
				  throw new AdminException("Invalid Customer Details,Please login First");
			  }
		  }
	}

}

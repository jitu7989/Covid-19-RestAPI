package com.coviwin.service;

import com.coviwin.exception.AdminException;
import com.coviwin.model.Admin;



public interface AdminService {
	
	public Admin registerCustomer(Admin customer)throws AdminException;
	
	public Admin updateCustomer(Admin customer,String key)throws AdminException;

}

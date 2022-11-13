package com.coviwin.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.User;



public interface UserDao extends JpaRepository<User, Integer>{

	public User findByMobileNo(String mobileNo);
	
	
}

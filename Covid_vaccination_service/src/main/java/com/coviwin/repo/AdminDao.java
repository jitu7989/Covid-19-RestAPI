package com.coviwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coviwin.model.Admin;


@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{
	
	public Admin findByMobileNo(String mobileNo);

}

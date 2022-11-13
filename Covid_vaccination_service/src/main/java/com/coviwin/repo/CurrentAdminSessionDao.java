package com.coviwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.CurrentAdminSession;



public interface CurrentAdminSessionDao extends JpaRepository<CurrentAdminSession, Integer>{
	public CurrentAdminSession findByUuid(String uuid);

}

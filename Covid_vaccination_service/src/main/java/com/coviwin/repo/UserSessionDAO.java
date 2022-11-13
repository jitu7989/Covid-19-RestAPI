package com.coviwin.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.coviwin.model.CurrentUserSession;



@Service
public interface UserSessionDAO extends JpaRepository<CurrentUserSession, Integer>{

	public CurrentUserSession findByUuid(String uuid);
}

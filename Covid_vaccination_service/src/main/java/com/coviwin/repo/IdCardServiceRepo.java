package com.coviwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coviwin.model.IdCard;
import com.coviwin.model.PanCard;

public interface IdCardServiceRepo extends JpaRepository<IdCard, Integer> {

	@Query("select i from IdCard i where i.panNo=?1")
	public IdCard getIdCardByPanno(String panNo);
	
//	@Query("select p from PanCard p where p.panNo=?1")
//	public PanCard getPanCardByPanno(String panNo);
	
	@Query("select i from IdCard i where i.adharNo=?1")
	public IdCard getIdCardByAadharNo(Long adharNo);
	
}

package com.coviwin.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.coviwin.exception.IdCardException;
import com.coviwin.model.AdharCard;
import com.coviwin.model.IdCard;
import com.coviwin.model.PanCard;
import com.coviwin.repo.IdCardServiceRepo;
import com.coviwin.service.IdCardService;

public class IdCardServiceImpl implements IdCardService {

	@Autowired
	private IdCardServiceRepo idCardRepo;
	
	@Override
	public IdCard getPanCardByNumber(String panNo)throws IdCardException {

		IdCard idcard = idCardRepo.getIdCardByPanno(panNo);
		
		if(idcard != null) {
		     return idcard;
		}else
			throw new IdCardException("Invalid PanNo...");
		
	}

	@Override
	public IdCard getAdharCardByNo(Long adharNo)throws IdCardException {

		IdCard idcard = idCardRepo.getIdCardByAadharNo(adharNo);
		
		if(idcard != null) {
		     return idcard;
		}else
			throw new IdCardException("Invalid adharNo...");
		
	}

	@Override
	public IdCard addIdCard(IdCard id) {

           return idCardRepo.save(id);
	}

}

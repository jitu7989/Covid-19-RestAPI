package com.coviwin.service;

import com.coviwin.exception.IdCardException;
import com.coviwin.model.IdCard;

public interface IdCardService {

	public IdCard getPanCardByNumber(String panNo) throws IdCardException;
	public IdCard getAdharCardByNo(Long adharNo)throws IdCardException;
	public IdCard addIdCard(IdCard id);
	
}

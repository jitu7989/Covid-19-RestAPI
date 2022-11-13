package com.coviwin.service;

import java.util.List;

import com.coviwin.exception.IdCardException;
import com.coviwin.model.IdCard;

public interface IdCardService {

	//all m
	public IdCard getPanCardByNumber(String panNo) throws IdCardException; 
	public List<IdCard> getAdharCardByNo(Long adharNo)throws IdCardException;
	public IdCard addIdCard(IdCard id) throws IdCardException;
	
}

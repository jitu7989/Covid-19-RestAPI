package com.coviwin.service;

public interface IdCardService {

	public IdCard getPanCardByNumber(String panNo);
	public IdCard getAdharCardByNo(Long adharNo);
	public IdCard addIdCard(IdCard id);
	
}

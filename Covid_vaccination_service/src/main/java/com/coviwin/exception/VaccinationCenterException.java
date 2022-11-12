package com.coviwin.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccinationCenterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VaccinationCenterException(String msg) {
		super(msg);
	}
}

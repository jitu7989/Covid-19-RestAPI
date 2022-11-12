package com.coviwin.exception;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ErrorDetail {
	
	private LocalDate timestamp;
	private String mssg;
	private String desc;
	
	public ErrorDetail() {
		this.timestamp = LocalDate.now();
	}
	
	public ErrorDetail(String msgg, String descString) {
		
		this();
		this.mssg = msgg;
		this.desc=descString;
		
	}
	
	
	
}

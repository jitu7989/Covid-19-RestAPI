package com.coviwin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity< ErrorDetail > methodArgumentNotValidExceptionHandler( MethodArgumentNotValidException manve , WebRequest wr ){
		
		return new ResponseEntity<ErrorDetail>
								( new ErrorDetail( manve.getMessage() , wr.getDescription(false) ) ,
								  HttpStatus.BAD_REQUEST );
	}
	 
	@ExceptionHandler(ApppintmentException.class)
	public ResponseEntity<ErrorDetail> appointmentExceptionHandler( ApppintmentException ae,  WebRequest wr ){
		
		ErrorDetail ed = new ErrorDetail( ae.getMessage() , wr.getDescription(false) );
		
		return new ResponseEntity<ErrorDetail>( ed , HttpStatus.BAD_REQUEST );
	}
	
	
	
	@ExceptionHandler(IdCardException.class)
	public ResponseEntity<ErrorDetail> idCardExceptionHandler( IdCardException ice , WebRequest wr ){
		
		ErrorDetail ed = new ErrorDetail( ice.getMessage() , wr.getDescription(false) );
		
		return new ResponseEntity<ErrorDetail>( ed , HttpStatus.BAD_REQUEST );
	}
	
	
	
	@ExceptionHandler(MemberException.class)
	public ResponseEntity<ErrorDetail> memeberExceptionHandler( MemberException me , WebRequest wr ){
		
		ErrorDetail ed = new ErrorDetail( me.getMessage() , wr.getDescription(false) );
		
		return new ResponseEntity<ErrorDetail>( ed , HttpStatus.BAD_REQUEST );
		
	}
	
	
	
	@ExceptionHandler( VaccineInventoryException.class )
	public ResponseEntity<ErrorDetail> vaccineInventoryExceptionHandler( VaccineInventoryException ve , WebRequest wr ){
		
		ErrorDetail ed = new ErrorDetail( ve.getMessage() , wr.getDescription(false) );
		
		return new ResponseEntity<ErrorDetail>( ed , HttpStatus.BAD_REQUEST );
	}
	
	
	
	@ExceptionHandler( VaccineRegistrationException.class )
	public ResponseEntity<ErrorDetail> vaccineRegistrationExceptionHandler( WebRequest wr ){
		
		ErrorDetail ed = new ErrorDetail( "" , wr.getDescription(false) );
		
		return new ResponseEntity<ErrorDetail>( ed , HttpStatus.BAD_REQUEST );
	}
	
	
}

package com.hcl.claimprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.claimprocessing.utils.ClaimConstants;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ClaimConstants.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(PolicyNotFoundException exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(CustomException.class)
//	public ResponseEntity<ErrorResponse> commonException(Exception exception, WebRequest request) {
//	
//		ErrorResponse errorResponse = new ErrorResponse(ClaimConstants.INTERNAL_SERVER_ERROR,
//				HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
}

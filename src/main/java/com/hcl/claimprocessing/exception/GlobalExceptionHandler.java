package com.hcl.claimprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(InfoException.class)
	public ResponseEntity<ErrorResponse> infoExistException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(PolicyNotExistException.class)
	public ResponseEntity<ErrorResponse> policyNotExistException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(UserNotExistException.class)
	public ResponseEntity<ErrorResponse> userNotExistException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(ValidInputException.class)
	public ResponseEntity<ErrorResponse> validInputException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<ErrorResponse> policyNotFoundException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(ClaimNotFoundException.class)
	public ResponseEntity<ErrorResponse> claimNotFoundException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> userException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(LoginDeniedException.class)
	public ResponseEntity<ErrorResponse> loginDeniedException(Exception e) {

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

}

package com.hcl.claimprocessing.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.exception.ValidInputException;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimController {
	
	@PostMapping("/")
	public void applyClaim(@RequestBody ClaimRequestDto claimRequestDto,BindingResult result) throws ValidInputException{
		if(result.hasErrors()) {
			throw new ValidInputException(result.getFieldError().getField()+":"+result.getFieldError().getDefaultMessage());
		}
		
	}
}

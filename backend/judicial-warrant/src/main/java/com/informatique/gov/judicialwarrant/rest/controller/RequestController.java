package com.informatique.gov.judicialwarrant.rest.controller;



import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.RequestHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/requests")
public class RequestController implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6878225451248023554L;
	
	private RequestHandler requestHandler;

	@GetMapping
	public ResponseEntity<?> getAll(Authentication authentication, @RequestParam(required = false) String typeCode, @RequestParam (required = false) String currentStatusCode) throws JudicialWarrantException {
		return  requestHandler.getAll(authentication, typeCode, currentStatusCode);
	}
	
}

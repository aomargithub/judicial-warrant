package com.informatique.gov.judicialwarrant.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.RequestStatusHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/requestStatus")
public class RequestStatusController {

	private RequestStatusHandler requestStatusHandler;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return requestStatusHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Byte id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return requestStatusHandler.getById(id, eTag);
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<?> getByCode(@PathVariable String code) throws JudicialWarrantException {
		return requestStatusHandler.getByCode(code);
	}

}

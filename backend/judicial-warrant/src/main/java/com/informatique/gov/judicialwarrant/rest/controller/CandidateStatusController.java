package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.CandidateStatusHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/candidateStatuses")
public class CandidateStatusController implements Serializable {

	
	private CandidateStatusHandler candidateStatusHandler ;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
    @GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return candidateStatusHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Byte id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return candidateStatusHandler.getById(id, eTag);
	}

	
	@GetMapping("/code/{code}")
	public ResponseEntity<?> getByCode(@PathVariable String code) throws JudicialWarrantException {
		return candidateStatusHandler.getByCode(code);
	}
	

}

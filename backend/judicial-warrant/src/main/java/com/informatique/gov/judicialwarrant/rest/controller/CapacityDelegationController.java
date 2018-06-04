package com.informatique.gov.judicialwarrant.rest.controller;



import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.handler.CapacityDelegationHandler;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/capacityDelegations")
public class CapacityDelegationController implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CapacityDelegationHandler capacityDelegationHandler;
	/**
	* 
	*/

	@GetMapping
	public ResponseEntity<?> getAll(Authentication authentication) throws JudicialWarrantException {
		return  capacityDelegationHandler.getAll(authentication);
	}

	@GetMapping("/serial={serial}")
	public ResponseEntity<?> getBySerial(Authentication authentication, @PathVariable String serial) throws JudicialWarrantException {
		return capacityDelegationHandler.getBySerial(authentication, serial);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> create(@RequestBody CapacityDelegationDto capacityDelegationDto) throws JudicialWarrantException {
		return capacityDelegationHandler.create(capacityDelegationDto);
	}
	
	@PutMapping(path = "/serial={serial}")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> update(@PathVariable String serial, @RequestBody CapacityDelegationDto capacityDelegationDto, 
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return capacityDelegationHandler.update(serial, capacityDelegationDto, eTag);
	}
	
	@PutMapping(path = "/serial={serial}/submission")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> submit(@PathVariable String serial, @RequestBody CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException {
		return capacityDelegationHandler.submit(serial, capacityDelegationChangeStatusRequest);
	}
	
}

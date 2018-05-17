package com.informatique.gov.judicialwarrant.rest.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.ErRequestHandler;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/erRequests")
public class ErRequestController {

	private ErRequestHandler requestHandler;
	/**
	* 
	*/

	@GetMapping
	public ResponseEntity<?> getAll(Authentication authentication) throws JudicialWarrantException {
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return  requestHandler.getAll();
		} else {
			return  requestHandler.getAllByOrganizationUnit();
		}
	}

	@GetMapping("/serial={serial}")
	public ResponseEntity<?> getBySerial(@PathVariable String serial, Authentication authentication) throws JudicialWarrantException {
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return requestHandler.getBySerial(serial);
		} else {
			return requestHandler.getBySerialAndOrganizationUnit(serial);
		}
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createRequest(@RequestBody ERRequestRequest erRequestRequest) throws JudicialWarrantException {
		return requestHandler.createRequest(erRequestRequest);
	}
	
	@PutMapping(path = "/serial={serial}/updateRequest")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> updateRequest(@PathVariable String serial, @RequestBody ERRequestRequest erRequestRequest,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return requestHandler.updateRequest(serial, erRequestRequest, eTag);
	}
	
	@PutMapping(path = "/serial={serial}/submitRequest")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> submitRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.submitRequest(serial, erRequestNotesData);
	}

	@PutMapping(path = "/serial={serial}/incompleteRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> incompleteRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.incompleteRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/rejectRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> rejectRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.rejectRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inprogressRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> inprogressRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.inprogressRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> inTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.inTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/passTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> passTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.passTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/failTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> failTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.failTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/issuedRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> issuedRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return requestHandler.issuedRequest(serial, erRequestNotesData);
	}
	
}

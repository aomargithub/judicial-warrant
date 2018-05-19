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
import com.informatique.gov.judicialwarrant.rest.handler.JwcdRequestHandler;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/jwcdRequests")
public class JwcdRequestController {

	private JwcdRequestHandler requestHandler;
	/**
	* 
	*/

	@GetMapping
	public ResponseEntity<?> getAll(Authentication authentication) throws JudicialWarrantException {
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return  requestHandler.getAll();
		} else {
			return requestHandler.getAllByOrganizationUnit();
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
	public ResponseEntity<?> createRequest(@RequestBody JwcdRequestData jobNameRequestData) throws JudicialWarrantException {
		return requestHandler.createRequest(jobNameRequestData);
	}
	
	@PutMapping(path = "/serial={serial}/updateRequest")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> updateRequest(@PathVariable String serial, @RequestBody JwcdRequestData jobNameRequestData, 
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return requestHandler.updateRequest(serial, jobNameRequestData, eTag);
	}
	
	@PutMapping(path = "/serial={serial}/submitRequest")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> submitRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.submitRequest(serial, jwcdRequestNotesData);
	}

	@PutMapping(path = "/serial={serial}/incompleteRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> incompleteRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.incompleteRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/rejectRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> rejectRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.rejectRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inprogressRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> inprogressRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.inprogressRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/lawAffairsReviewRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> lawAffairsReviewRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.lawAffairsReviewRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/lawAffairsAcceptRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> lawAffairsAcceptRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.lawAffairsAcceptRequest(serial, jwcdRequestNotesData);
	}

	@PutMapping(path = "/serial={serial}/lawAffairsRejectRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> lawAffairsRejectRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.lawAffairsRejectRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/issuedRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> issuedRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.issuedRequest(serial, jwcdRequestNotesData);
	}
	
}

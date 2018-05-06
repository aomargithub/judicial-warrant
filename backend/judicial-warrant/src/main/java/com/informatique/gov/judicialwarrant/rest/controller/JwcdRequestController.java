package com.informatique.gov.judicialwarrant.rest.controller;


import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return  requestHandler.getAll();
	}

	@GetMapping("/serial={serial}")
//	@RolesAllowed("userRole")
	public ResponseEntity<?> getBySerial(@PathVariable String serial) throws JudicialWarrantException {
		return requestHandler.getBySerial(serial);
	}
	
	@PostMapping
//	@RolesAllowed("userRole")
	public ResponseEntity<?> createRequest(@RequestBody JwcdRequestData jobNameRequestData) throws JudicialWarrantException {
		return requestHandler.createRequest(jobNameRequestData);
	}
	
	@PutMapping(path = "/serial={serial}/updateRequest")
//	@RolesAllowed("userRole")
	public ResponseEntity<?> updateRequest(@PathVariable String serial, @RequestBody JwcdRequestData jobNameRequestData, 
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return requestHandler.updateRequest(serial, jobNameRequestData, eTag);
	}
	
	@PutMapping(path = "/serial={serial}/submitRequest")
//	@RolesAllowed("userRole")
	public ResponseEntity<?> submitRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.submitRequest(serial, jwcdRequestNotesData);
	}

	@PutMapping(path = "/serial={serial}/incompleteRequest")
//	@RolesAllowed("adminRole")
	public ResponseEntity<?> incompleteRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.incompleteRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/rejectRequest")
//	@RolesAllowed("adminRole")
	public ResponseEntity<?> rejectRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.rejectRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inprogressRequest")
//	@RolesAllowed("adminRole")
	public ResponseEntity<?> inprogressRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.inprogressRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/lawAffairsReviewRequest")
//	@RolesAllowed("adminRole")
	public ResponseEntity<?> lawAffairsReviewRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.lawAffairsReviewRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/lawAffairsAcceptRequest")
//	@RolesAllowed("adminRole")
	public ResponseEntity<?> lawAffairsAcceptRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.lawAffairsAcceptRequest(serial, jwcdRequestNotesData);
	}

	@PutMapping(path = "/serial={serial}/lawAffairsRejectRequest")
//	@RolesAllowed("adminRole")
	public ResponseEntity<?> lawAffairsRejectRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.lawAffairsRejectRequest(serial, jwcdRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/issuedRequest")
//	@RolesAllowed("adminRole")
	public ResponseEntity<?> issuedRequest(@PathVariable String serial, @RequestBody JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		return requestHandler.issuedRequest(serial, jwcdRequestNotesData);
	}
	
}

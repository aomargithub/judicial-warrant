package com.informatique.gov.judicialwarrant.rest.controller;



import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledRegistrationHandler;
import com.informatique.gov.judicialwarrant.rest.request.EntitledRegistrationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.support.validator.EntitledAttachmentsValidator;
import com.informatique.gov.judicialwarrant.support.validator.EntitledRegistrationAttachmentsValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entitledRegistrations")
public class EntitledRegistrationController {

	private EntitledRegistrationHandler entitledRegistrationHandlerHandler;
	private EntitledRegistrationAttachmentsValidator entitledRegistrationAttachmentsValidator;
	private EntitledAttachmentsValidator entitledAttachmentsValidator;
	/**
	* 
	*/

	@PreAuthorize("hasRole('ROLE_USER')")
	@InitBinder("entitledRegistrationChangeStatusRequest")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(entitledRegistrationAttachmentsValidator, entitledAttachmentsValidator);
	}
	
	@GetMapping("/report/serial={serial}")
	@ResponseBody
	public ResponseEntity<?> generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response,
			@PathVariable String serial) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.generateEntitledRegistrationReportByRequestSerial(response, serial);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(Authentication authentication) throws JudicialWarrantException {
		return  entitledRegistrationHandlerHandler.getAll(authentication);
	}

	@GetMapping("/serial={serial}")
	public ResponseEntity<?> getBySerial(@PathVariable String serial, Authentication authentication) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getBySerial(authentication, serial);		
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> create(@RequestBody EntitledRegistrationDto entitledRegistrationDto) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.create(entitledRegistrationDto);
	}
	
	@PutMapping(path = "/serial={serial}/updateRequest")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> update(@PathVariable String serial, @RequestBody EntitledRegistrationDto entitledRegistrationDto,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.update(serial, entitledRegistrationDto, eTag);
	}
	
	@PutMapping(path = "/serial={serial}/submitRequest")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> submitRequest(@PathVariable String serial, @Valid @RequestBody EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.submit(serial, entitledRegistrationChangeStatusRequest);
	}

	/*@PutMapping(path = "/serial={serial}/incompleteRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> incompleteRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.incompleteRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/rejectRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> rejectRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.rejectRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inprogressRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> inprogressRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.inprogressRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> inTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.inTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/passTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> passTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.passTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/failTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> failTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.failTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/issuedRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> issuedRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.issuedRequest(serial, erRequestNotesData);
	}*/
	
}

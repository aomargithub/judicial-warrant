package com.informatique.gov.judicialwarrant.rest.controller;



import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
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
	private static final long serialVersionUID = 6878225451248023554L;
	
	private CapacityDelegationHandler capacityDelegationHandler;

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
	public ResponseEntity<?> submit(@RequestBody CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest, @PathVariable String serial) throws JudicialWarrantException {
		return capacityDelegationHandler.submit(serial, capacityDelegationChangeStatusRequest);
	}
	
	@GetMapping(path = "/serial={serial}/RequestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> getRequestAttachmentById(@PathVariable String serial, @PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return capacityDelegationHandler.getRequestAttachmentById(serial, id, eTag);
	}
	
	@GetMapping(path = "/serial={serial}/RequestAttachments")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> getAllRequestAttachmentByRequestSerial(@PathVariable String serial,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return capacityDelegationHandler.getAllRequestAttachmentByRequestSerial(serial);
	}

	@PostMapping(path = "/serial={serial}/RequestAttachments")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> createRequestAttachment(@PathVariable String serial, @Valid @RequestPart("dto") RequestAttachmentDto dto, @RequestPart("file") MultipartFile file) throws JudicialWarrantException {
		return capacityDelegationHandler.createRequestAttachment(serial, dto, file);
	}

	@PutMapping("/serial={serial}/RequestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> updateRequestAttachment(@PathVariable String serial, @Valid @RequestBody RequestAttachmentDto dto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return capacityDelegationHandler.updateRequestAttachment(serial, dto, id, eTag);
	}

	@DeleteMapping("/serial={serial}/RequestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> deleteRequestAttachment(@PathVariable String serial, @PathVariable Long id) throws JudicialWarrantException {
		return capacityDelegationHandler.deleteRequestAttachment(serial, id);
	}
	
}

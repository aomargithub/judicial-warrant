package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.handler.AttachmentTypeHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/attachmentTypes")
public class AttachmentTypeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8218542953772806708L;

	private AttachmentTypeHandler attachmentTypeHandler;

	
    @GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return attachmentTypeHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return attachmentTypeHandler.getById(id, eTag);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OFFICER')")
	public ResponseEntity<?> save(@Valid @RequestBody AttachmentTypeDto attachmentTypeDto) throws JudicialWarrantException {
		return attachmentTypeHandler.save(attachmentTypeDto);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OFFICER')")
	public ResponseEntity<?> update(@Valid @RequestBody AttachmentTypeDto attachmentTypeDto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return attachmentTypeHandler.update(attachmentTypeDto, id, eTag);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OFFICER')")
	public ResponseEntity<?> delete(@PathVariable Long id) throws JudicialWarrantException {
		return attachmentTypeHandler.delete(id);
	}

}

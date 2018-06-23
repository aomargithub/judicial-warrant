package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestTypeAttachmentTypeHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/requestTypeAttachmentTypes")
public class RequestTypeAttachmentTypeController implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7701627550236801076L;
	private RequestTypeAttachmentTypeHandler requestTypeAttachmentTypeHandler;
	
	 @GetMapping
		public ResponseEntity<?> getAll() throws JudicialWarrantException {
			return requestTypeAttachmentTypeHandler.getAll();
		}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Short id,
			@RequestHeader(name = "If-None-Match", required = false) Short etag) throws JudicialWarrantException {
		return requestTypeAttachmentTypeHandler.getById(id, etag);
	}

	/*@GetMapping(params= {"requestTypeId"})
	public ResponseEntity<?> getByRequestTypeId(@RequestParam(name = "requestTypeId") Byte requestTypeId) throws JudicialWarrantException {
		return requestTypeAttachmentTypeHandler.getByRequestTypeId(requestTypeId);
	}*/
	
	@GetMapping(params= {"requestTypeCode"})
	public ResponseEntity<?> getByRequestTypeCode(@RequestParam(name = "requestTypeCode") String requestTypeCode) throws JudicialWarrantException {
		return requestTypeAttachmentTypeHandler.getByRequestTypeCode(requestTypeCode);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OFFICER')")
	public ResponseEntity<?> save(@RequestBody RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto)
			throws JudicialWarrantException {
		return requestTypeAttachmentTypeHandler.save(requestTypeAttachmentTypeDto);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OFFICER')")
	public ResponseEntity<?> update(@RequestBody RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto,
			@PathVariable Short id, @RequestHeader(name = "If-Match") Short etag) throws JudicialWarrantException {
		return requestTypeAttachmentTypeHandler.update(requestTypeAttachmentTypeDto, id, etag);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OFFICER')")
	public ResponseEntity<?> delete(@PathVariable Short id) throws JudicialWarrantException {
		return requestTypeAttachmentTypeHandler.delete(id);
	}

}

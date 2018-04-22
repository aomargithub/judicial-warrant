package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
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

	
	private AttachmentTypeHandler attachmentTypeHandler ;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
    @GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return attachmentTypeHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Byte id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return attachmentTypeHandler.getById(id, eTag);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody AttachmentTypeDto attachmentTypeDto) throws JudicialWarrantException {
		return attachmentTypeHandler.save(attachmentTypeDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody AttachmentTypeDto attachmentTypeDto, @PathVariable Byte id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return attachmentTypeHandler.update(attachmentTypeDto, id, eTag);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Byte id) throws JudicialWarrantException {
		return attachmentTypeHandler.delete(id);
	}

}

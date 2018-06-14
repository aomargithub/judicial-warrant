package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledAttachmentHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entitledAttachments")
public class EntitledAttachmentController implements Serializable {

	
	private EntitledAttachmentHandler entitledAttachmentHandler ;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
    
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("entiteldId") Long entiteldId) throws JudicialWarrantException {
		return entitledAttachmentHandler.getAllByEntitledId(entiteldId);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledAttachmentHandler.getById(id, eTag);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody EntitledAttachmentDto dto, @RequestParam("file") MultipartFile file) throws JudicialWarrantException {
		return entitledAttachmentHandler.save(dto, file);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody EntitledAttachmentDto dto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledAttachmentHandler.update(dto, id, eTag);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws JudicialWarrantException {
		return entitledAttachmentHandler.delete(id);
	}
	
}

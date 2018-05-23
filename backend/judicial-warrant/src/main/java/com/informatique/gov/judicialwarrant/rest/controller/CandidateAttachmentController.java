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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.CandidateAttachmentHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/candidateAttachments")
public class CandidateAttachmentController implements Serializable {

	
	private CandidateAttachmentHandler candidateAttachmentHandler ;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
    @GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return candidateAttachmentHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return candidateAttachmentHandler.getById(id, eTag);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestPart("file") MultipartFile file, @RequestPart("dto") CandidateAttachmentDto candidateAttachmentDto) throws JudicialWarrantException {
		return candidateAttachmentHandler.save(file, candidateAttachmentDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody CandidateAttachmentDto candidateAttachmentDto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return candidateAttachmentHandler.update(candidateAttachmentDto, id, eTag);
	}
	
	@PutMapping("/{id}/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return candidateAttachmentHandler.uploadFile(file, id, eTag);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws JudicialWarrantException {
		return candidateAttachmentHandler.delete(id);
	}

}

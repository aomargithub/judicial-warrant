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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestAttachmentHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/requestAttachments")
public class RequestAttachmentController implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -7714345226533571986L;
	private RequestAttachmentHandler requestAttachmentHandler;

	@GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return requestAttachmentHandler.getAll();
	}

	@GetMapping(path = "/serial={serial}/{id}")
	public ResponseEntity<?> getById(@PathVariable String serial, @PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return requestAttachmentHandler.getById(serial, id, eTag);
	}
	
	@GetMapping(path = "/serial={serial}")
	public ResponseEntity<?> getAllByRequestSerial(@PathVariable String serial,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return requestAttachmentHandler.getAllByRequestSerial(serial);
	}

	@PostMapping(path = "/serial={serial}")
	public ResponseEntity<?> create(@PathVariable String serial, @Valid @RequestPart("requestAttachment") RequestAttachmentDto requestAttachment, @RequestPart("file") MultipartFile file) throws JudicialWarrantException {
		return requestAttachmentHandler.create(serial, requestAttachment, file);
	}

	@PutMapping("/serial={serial}/{id}")
	public ResponseEntity<?> update(@PathVariable String serial, @Valid @RequestBody RequestAttachmentDto dto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return requestAttachmentHandler.update(serial, dto, id, eTag);
	}

	@DeleteMapping("/serial={serial}/{id}")
	public ResponseEntity<?> delete(@PathVariable String serial, @PathVariable Long id) throws JudicialWarrantException {
		return requestAttachmentHandler.delete(serial, id);
	}

}

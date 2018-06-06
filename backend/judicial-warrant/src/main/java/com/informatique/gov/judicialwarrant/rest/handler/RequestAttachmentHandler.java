package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;

public interface RequestAttachmentHandler {
	ResponseEntity<List<RequestAttachmentDto>> getAll() throws JudicialWarrantException;
	ResponseEntity<List<RequestAttachmentDto>> getAllByRequestSerial(String serial) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> getById(Long id, Short etag) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> update(RequestAttachmentDto dto, Long id, Short etag)
			throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> create(RequestAttachmentDto dto)
			throws JudicialWarrantException;

	ResponseEntity<Void> delete(Long id) throws JudicialWarrantException;
}

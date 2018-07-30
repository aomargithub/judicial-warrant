package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;

public interface RequestAttachmentHandler {
	ResponseEntity<List<RequestAttachmentDto>> getAll() throws JudicialWarrantException;
	ResponseEntity<List<RequestAttachmentDto>> getAllByRequestSerial(String serial) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> getById(String serial, Long id, Short etag) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> update(String serial, RequestAttachmentDto dto, Long id, Short etag)
			throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> create(String serial, RequestAttachmentDto dto, MultipartFile file)
			throws JudicialWarrantException;

	ResponseEntity<Void> delete(String serial, Long id) throws JudicialWarrantException;
}

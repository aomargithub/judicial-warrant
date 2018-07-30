package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;

public interface RequestTypeAttachmentTypeHandler extends Serializable{
	
	ResponseEntity<List<RequestTypeAttachmentTypeDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<RequestTypeAttachmentTypeDto> getById(Short id, Short etag) throws JudicialWarrantException;

	ResponseEntity<List<RequestTypeAttachmentTypeDto>> getByRequestTypeId(Byte id) throws JudicialWarrantException;
	
	ResponseEntity<RequestTypeAttachmentTypeDto> save(RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto) throws JudicialWarrantException;
	
	ResponseEntity<RequestTypeAttachmentTypeDto> update(RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto, Short id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<RequestTypeAttachmentTypeDto> delete(Short id) throws JudicialWarrantException;

	ResponseEntity<List<AttachmentTypeDto>> getAttachmentTypesByRequestTypeCode(String requestTypeCode)
			throws JudicialWarrantException;

}
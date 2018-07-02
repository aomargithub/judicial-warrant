package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;

public interface CapacityDelegationHandler {
	ResponseEntity<List<CapacityDelegationDto>> getAll(Authentication authentication, String status) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> getBySerial(Authentication authentication, String serial) throws JudicialWarrantException;
	
	
	ResponseEntity<CapacityDelegationDto> create(CapacityDelegationDto capacityDelegationDto) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> update(String serial, CapacityDelegationDto capacityDelegationDto, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> submit(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<List<RequestAttachmentDto>> getAllRequestAttachmentByRequestSerial(String serial) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> getRequestAttachmentById(String serial, Long id, Short etag) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> updateRequestAttachment(String serial, RequestAttachmentDto dto, Long id, Short etag)
			throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> createRequestAttachment(String serial, RequestAttachmentDto dto, MultipartFile file)
			throws JudicialWarrantException;

	ResponseEntity<?> deleteRequestAttachment(String serial, Long id) throws JudicialWarrantException;
	
	ResponseEntity<byte[]> downloadFile(String serial, Long id, String ucmDocumentId) throws JudicialWarrantException;
	
}

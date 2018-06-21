package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.request.EntitledRegistrationChangeStatusRequest;

public interface EntitledRegistrationHandler extends Serializable{

	ResponseEntity<?> generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response, String serial)
			throws JudicialWarrantException;
	
	ResponseEntity<List<EntitledRegistrationDto>> getAll(Authentication authentication) throws JudicialWarrantException;

	ResponseEntity<EntitledRegistrationDto> getBySerial(Authentication authentication, String serial)
			throws JudicialWarrantException;

	ResponseEntity<EntitledRegistrationDto> create(EntitledRegistrationDto entitledRegistrationDto)
			throws JudicialWarrantException;

	ResponseEntity<EntitledRegistrationDto> update(String serial, EntitledRegistrationDto entitledRegistrationDto,
			Short etag) throws JudicialWarrantException;
	
	ResponseEntity<EntitledRegistrationDto> submit(String serial,
			EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException;

	ResponseEntity<List<RequestAttachmentDto>> getAllRequestAttachmentByRequestSerial(String serial) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> getRequestAttachmentById(String serial, Long id, Short etag) throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> updateRequestAttachment(String serial, RequestAttachmentDto dto, Long id, Short etag)
			throws JudicialWarrantException;

	ResponseEntity<RequestAttachmentDto> createRequestAttachment(String serial, RequestAttachmentDto dto, MultipartFile file)
			throws JudicialWarrantException;

	ResponseEntity<Void> deleteRequestAttachment(String serial, Long id) throws JudicialWarrantException;
	
	ResponseEntity<EntitledDto> saveEntitled(EntitledDto entitledDto) throws JudicialWarrantException;
	
	ResponseEntity<EntitledDto> updateEntitled(EntitledDto entitledDto, Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<EntitledDto> getEntitledById(Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<List<EntitledDto>> getAllEntitledsByEntitledRegistrationSerial(String serial) throws JudicialWarrantException;
	
	ResponseEntity<Void> deleteEntitled(Long id) throws JudicialWarrantException;

	ResponseEntity<EntitledAttachmentDto> saveEntitledAttachment(EntitledAttachmentDto dto, MultipartFile file) throws JudicialWarrantException;
	
	ResponseEntity<EntitledAttachmentDto> updateEntitledAttachment(EntitledAttachmentDto dto, Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<EntitledAttachmentDto> getEntitledAttachmentById(Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<List<EntitledAttachmentDto>> getAllEntitledAttachmentByEntitledId(Long id) throws JudicialWarrantException;
	
	ResponseEntity<Void> deleteEntitledAttachment(Long id) throws JudicialWarrantException;

	
}

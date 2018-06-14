package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;

public interface EntitledAttachmentHandler extends Serializable {

	ResponseEntity<EntitledAttachmentDto> save(EntitledAttachmentDto dto, MultipartFile file) throws JudicialWarrantException;
	
	ResponseEntity<EntitledAttachmentDto> update(EntitledAttachmentDto dto, Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<EntitledAttachmentDto> getById(Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<List<EntitledAttachmentDto>> getAllByEntitledId(Long id) throws JudicialWarrantException;
	
	ResponseEntity<Void> delete(Long id) throws JudicialWarrantException;

}

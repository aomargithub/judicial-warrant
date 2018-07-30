package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;

public interface EntitledAttachmentService extends Serializable{
	
	Short getVersionById(Long id) throws JudicialWarrantException;
	
	EntitledAttachmentDto getById(Long id) throws JudicialWarrantException;
	
	byte[] downloadFile(String serial, Long id, String ucmDocumentId) throws JudicialWarrantException;
	
	EntitledAttachmentDto save(EntitledAttachmentDto dto, MultipartFile file) throws JudicialWarrantException;
	
	EntitledAttachmentDto update(EntitledAttachmentDto dto) throws JudicialWarrantException;
	
	void delete(Long id) throws JudicialWarrantException;
	
	void deleteByEntitledId(Long id) throws JudicialWarrantException;
	
	List<EntitledAttachmentDto> getByEntitledId(Long id) throws JudicialWarrantException;
	
}

package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;

public interface EntitledAttachmentService extends Serializable{
	
	void delete(Long id) throws JudicialWarrantException;
	
	void deleteByEntitledId(Long id) throws JudicialWarrantException;
	
	List<EntitledAttachmentDto> getByEntitledId(Long id) throws JudicialWarrantException;
	
}

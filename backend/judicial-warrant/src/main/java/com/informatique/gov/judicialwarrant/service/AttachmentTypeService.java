package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;

public interface AttachmentTypeService extends Serializable{
	List<AttachmentTypeDto> getAll() throws JudicialWarrantException;

	AttachmentTypeDto save(final AttachmentTypeDto dto) throws JudicialWarrantException;

	AttachmentTypeDto getById(Long id) throws JudicialWarrantException;

	AttachmentTypeDto update(AttachmentTypeDto dto) throws JudicialWarrantException;

	public Short getVersionById(Long id) throws JudicialWarrantException;
	
	void delete(Long id) throws JudicialWarrantException;

}

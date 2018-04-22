package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;

public interface AttachmentTypeService extends Serializable{
	List<AttachmentTypeDto> getAll() throws JudicialWarrantException;

	AttachmentTypeDto save(final AttachmentTypeDto dto) throws JudicialWarrantException;

	AttachmentTypeDto getById(Byte id) throws JudicialWarrantException;

	AttachmentTypeDto update(AttachmentTypeDto dto) throws JudicialWarrantException;

	public Short getVersionById(Byte id) throws JudicialWarrantException;
	
	void delete(Byte id) throws JudicialWarrantException;

}

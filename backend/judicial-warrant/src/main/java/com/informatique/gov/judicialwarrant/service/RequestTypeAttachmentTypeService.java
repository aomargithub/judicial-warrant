package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;

public interface RequestTypeAttachmentTypeService extends Serializable{

	RequestTypeAttachmentTypeDto getById(Short id) throws JudicialWarrantException;
	
	Short getVersionById(Short id) throws JudicialWarrantException;

	List<RequestTypeAttachmentTypeDto> getByRequestTypeId(Byte id) throws JudicialWarrantException;
	
	RequestTypeAttachmentTypeDto save(RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto) throws JudicialWarrantException;
	
	RequestTypeAttachmentTypeDto update(RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto) throws JudicialWarrantException;
	
	void delete(Short id) throws JudicialWarrantException;

}
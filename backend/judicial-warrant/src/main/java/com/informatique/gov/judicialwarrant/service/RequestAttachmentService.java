package com.informatique.gov.judicialwarrant.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;

public interface RequestAttachmentService {

	List<RequestAttachmentDto> getAll()throws JudicialWarrantException;
	List<RequestAttachmentDto> getAllByRequestSerial(String serial)throws JudicialWarrantException;
	RequestAttachmentDto getById(Long id) throws JudicialWarrantException;
	RequestAttachmentDto create(RequestAttachmentDto requestAttachmentDto, MultipartFile file)throws JudicialWarrantException;
	RequestAttachmentDto update(RequestAttachmentDto requestAttachmentDto,Long id)throws JudicialWarrantException;
	void delete(Long id) throws JudicialWarrantInternalException;
	public Short getVersionById(Long id) throws JudicialWarrantException;
	
}

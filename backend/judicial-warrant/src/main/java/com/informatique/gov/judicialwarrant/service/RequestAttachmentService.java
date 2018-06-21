package com.informatique.gov.judicialwarrant.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;

public interface RequestAttachmentService {

	List<RequestAttachmentDto> getAll()throws JudicialWarrantException;
	List<RequestAttachmentDto> getAllByRequestSerial(String serial)throws JudicialWarrantException;
	RequestAttachmentDto getById(String serial, Long id) throws JudicialWarrantException;
	RequestAttachmentDto create(String serial, RequestAttachmentDto requestAttachmentDto, MultipartFile file)throws JudicialWarrantException;
	RequestAttachmentDto update(String serial, RequestAttachmentDto requestAttachmentDto,Long id)throws JudicialWarrantException;
	void delete(String serial, Long id) throws JudicialWarrantInternalException;
	public Short getVersionById(String serial, Long id) throws JudicialWarrantException;
	
}

package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;

public interface CandidateAttachmentService extends Serializable{
	List<CandidateAttachmentDto> getAll() throws JudicialWarrantException;

	CandidateAttachmentDto save(MultipartFile file, final CandidateAttachmentDto dto) throws JudicialWarrantException;

	CandidateAttachmentDto getById(Long id) throws JudicialWarrantException;

	CandidateAttachmentDto update(CandidateAttachmentDto dto) throws JudicialWarrantException;
	
	CandidateAttachmentDto uploadFile(MultipartFile file, Long id) throws JudicialWarrantException;

	public Short getVersionById(Long id) throws JudicialWarrantException;
	
	void delete(Long id) throws JudicialWarrantException;
	
	void deleteCandidateAttachmentsByRequestId(Long id) throws JudicialWarrantException;

}

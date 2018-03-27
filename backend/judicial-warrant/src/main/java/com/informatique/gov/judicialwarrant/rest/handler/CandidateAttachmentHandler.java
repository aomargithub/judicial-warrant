package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;

public interface CandidateAttachmentHandler extends Serializable {
	ResponseEntity<List<CandidateAttachmentDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<CandidateAttachmentDto> getById(Long id, Short etag) throws JudicialWarrantException;

	ResponseEntity<CandidateAttachmentDto> update(CandidateAttachmentDto candidateAttachmentDto, Long id, Short etag)
			throws JudicialWarrantException;

	ResponseEntity<CandidateAttachmentDto> save(CandidateAttachmentDto candidateAttachmentDto)
			throws JudicialWarrantException;

	ResponseEntity<Void> delete(Long id) throws JudicialWarrantException;
}

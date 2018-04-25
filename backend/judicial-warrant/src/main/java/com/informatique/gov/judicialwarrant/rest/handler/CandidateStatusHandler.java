package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateStatusDto;

public interface CandidateStatusHandler extends Serializable {
	ResponseEntity<List<CandidateStatusDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<CandidateStatusDto> getById(Byte id, Short etag) throws JudicialWarrantException;

	ResponseEntity<CandidateStatusDto> getByCode(String code) throws JudicialWarrantException;
	
}

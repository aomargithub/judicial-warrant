package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateStatusDto;
import com.informatique.gov.judicialwarrant.rest.handler.CandidateStatusHandler;
import com.informatique.gov.judicialwarrant.service.CandidateStatusService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class CandidateStatusHandlerImpl implements CandidateStatusHandler {
	private CandidateStatusService candidateStatusService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ResponseEntity<List<CandidateStatusDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<CandidateStatusDto>> response = null;
		try {
			List<CandidateStatusDto> dtos = candidateStatusService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}

	@Override
	public ResponseEntity<CandidateStatusDto> getById(Byte id, Short etag) throws JudicialWarrantException {
		ResponseEntity<CandidateStatusDto> response = null;
		try {
			notNull(id, "id must be set");
			CandidateStatusDto dto = null;
			
			dto = candidateStatusService.getById(id);
			
			if(dto == null) {
				throw new ResourceNotFoundException(id);
			}
			
			response = ResponseEntity.ok().body(dto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return response;
	}

	@Override
	public ResponseEntity<CandidateStatusDto> getByCode(String code) throws JudicialWarrantException {
		ResponseEntity<CandidateStatusDto> response = null;
		try {
			CandidateStatusDto dto = candidateStatusService.getByCode(code);
			if (dto == null) {
				throw new ResourceNotFoundException(code);
			}
			response = ResponseEntity.ok().body(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	
}

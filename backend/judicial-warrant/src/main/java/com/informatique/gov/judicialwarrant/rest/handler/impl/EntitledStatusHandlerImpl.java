package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledStatusHandler;
import com.informatique.gov.judicialwarrant.service.EntitledStatusService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class EntitledStatusHandlerImpl implements EntitledStatusHandler {
	private EntitledStatusService candidateStatusService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ResponseEntity<List<EntitledStatusDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<EntitledStatusDto>> response = null;
		try {
			List<EntitledStatusDto> dtos = candidateStatusService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}

	@Override
	public ResponseEntity<EntitledStatusDto> getById(Byte id, Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledStatusDto> response = null;
		try {
			notNull(id, "id must be set");
			EntitledStatusDto dto = null;
			
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
	public ResponseEntity<EntitledStatusDto> getByCode(String code) throws JudicialWarrantException {
		ResponseEntity<EntitledStatusDto> response = null;
		try {
			EntitledStatusDto dto = candidateStatusService.getByCode(code);
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

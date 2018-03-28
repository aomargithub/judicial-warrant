package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestStatusHandler;
import com.informatique.gov.judicialwarrant.service.RequestStatusService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RequestStatusHandlerImpl implements RequestStatusHandler {
	private RequestStatusService requestStatusService;

	@Override
	public ResponseEntity<List<RequestStatusDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<RequestStatusDto>> response = null;
		try {
			List<RequestStatusDto> dtos = requestStatusService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestStatusDto> getById(Byte id, Short etag) throws JudicialWarrantException {
		ResponseEntity<RequestStatusDto> response = null;
		try {
			notNull(id, "id must be set");
			RequestStatusDto dto = null;

			dto = requestStatusService.getById(id);

			if (dto == null) {
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
	public ResponseEntity<RequestStatusDto> getByCode(String code) throws JudicialWarrantException {
		ResponseEntity<RequestStatusDto> response = null;
		try {
			RequestStatusDto dto = requestStatusService.getByCode(code);
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

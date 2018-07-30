package com.informatique.gov.judicialwarrant.rest.handler.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestHandler;
import com.informatique.gov.judicialwarrant.service.RequestService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RequestHandlerImpl implements RequestHandler {
	
	private RequestService requestService;

	@Override
	public ResponseEntity<List<RequestDto>> getAll(Authentication authentication, String typeCode,
			String currentStatusCode) throws JudicialWarrantException {
		ResponseEntity<List<RequestDto>> response = null;
		try {
			List<RequestDto> dtos = requestService.getAll(authentication, typeCode, currentStatusCode);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

}

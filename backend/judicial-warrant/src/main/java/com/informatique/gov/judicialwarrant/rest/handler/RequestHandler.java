package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

public interface RequestHandler {

	ResponseEntity<List<RequestDto>> getAll(Authentication authentication, String typeCode,
			String currentStatusCode) throws JudicialWarrantException;

}

package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;

public interface EntitledRegistrationHandler extends Serializable{

	ResponseEntity<?> generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response, String serial)
			throws JudicialWarrantException;
	
	ResponseEntity<List<EntitledRegistrationDto>> getAll(Authentication authentication) throws JudicialWarrantException;

	ResponseEntity<EntitledRegistrationDto> getBySerial(Authentication authentication, String serial)
			throws JudicialWarrantException;

	ResponseEntity<EntitledRegistrationDto> create(EntitledRegistrationDto entitledRegistrationDto)
			throws JudicialWarrantException;

	ResponseEntity<EntitledRegistrationDto> update(String serial, EntitledRegistrationDto entitledRegistrationDto,
			Short etag) throws JudicialWarrantException;
	
	
}

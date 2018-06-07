package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.request.EntitledRegistrationChangeStatusRequest;

public interface EntitledRegistrationService extends Serializable {
	
	void generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response, String serial) throws JudicialWarrantException;
	
	List<EntitledRegistrationDto> getAll(Authentication  authentication) throws JudicialWarrantException;
	
	EntitledRegistrationDto create(EntitledRegistrationDto entitledRegistrationDto) throws JudicialWarrantException;

	Short getVersionBySerial(String serial) throws JudicialWarrantException;
	
	EntitledRegistrationDto getBySerial(Authentication  authentication, String serial) throws JudicialWarrantException;

	EntitledRegistrationDto update(String serial, EntitledRegistrationDto dto) throws JudicialWarrantException;

	EntitledRegistrationDto submit(String serial,
			EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException;

	EntitledRegistrationDto incomplete(String serial,
			EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException;
	
}

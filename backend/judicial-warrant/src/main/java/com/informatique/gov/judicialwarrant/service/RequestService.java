package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

public interface RequestService extends Serializable {

	List<RequestDto> getAll(Authentication authentication, String typeCode, String currentStatusCode) throws JudicialWarrantException;

}

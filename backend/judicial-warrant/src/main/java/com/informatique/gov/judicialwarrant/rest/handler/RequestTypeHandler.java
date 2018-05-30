package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;

public interface RequestTypeHandler extends Serializable{

	ResponseEntity<RequestTypeDto> getByCode(String code) throws JudicialWarrantException;

	ResponseEntity<List<RequestTypeDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<List<RequestTypeDto>> getByIsActive(Boolean isActive) throws JudicialWarrantException;

}

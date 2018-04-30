package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;

public interface RequestStatusHandler {
	ResponseEntity<List<RequestStatusDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<RequestStatusDto> getById(Byte id, Short etag) throws JudicialWarrantException;

	ResponseEntity<RequestStatusDto> getByCode(String code) throws JudicialWarrantException;

}

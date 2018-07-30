package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;

public interface EntitledStatusHandler extends Serializable {
	ResponseEntity<List<EntitledStatusDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<EntitledStatusDto> getById(Byte id, Short etag) throws JudicialWarrantException;

	ResponseEntity<EntitledStatusDto> getByCode(String code) throws JudicialWarrantException;
	
}

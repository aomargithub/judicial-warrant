package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;

public interface EntitledHandler extends Serializable {

	ResponseEntity<EntitledDto> save(EntitledDto entitledDto) throws JudicialWarrantException;
	
	ResponseEntity<EntitledDto> update(EntitledDto entitledDto, Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<EntitledDto> getById(Long id, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<List<EntitledDto>> getAllByEntitledRegistrationSerial(String serial) throws JudicialWarrantException;
	
	ResponseEntity<Void> delete(Long id) throws JudicialWarrantException;

}

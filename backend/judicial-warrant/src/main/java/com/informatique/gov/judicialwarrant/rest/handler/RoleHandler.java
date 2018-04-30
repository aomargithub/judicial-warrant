package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;

public interface RoleHandler extends Serializable{
	ResponseEntity<List<RoleDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<RoleDto> getById(Byte id) throws JudicialWarrantException;

	
}

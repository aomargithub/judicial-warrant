package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;

public interface RoleService extends Serializable{
	List<RoleDto> getAll() throws JudicialWarrantException;
	RoleDto getById(Byte id) throws JudicialWarrantException;

}

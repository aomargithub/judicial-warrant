package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;

public interface EntitledStatusService extends Serializable{
	List<EntitledStatusDto> getAll() throws JudicialWarrantException;

	EntitledStatusDto getById(Byte id) throws JudicialWarrantException;

	EntitledStatusDto getByCode(String code) throws JudicialWarrantException;
	
}

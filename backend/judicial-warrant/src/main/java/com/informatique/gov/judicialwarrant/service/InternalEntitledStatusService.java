package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;

public interface InternalEntitledStatusService extends Serializable{
	EntitledStatusDto getByCode(String code) throws JudicialWarrantException;
}

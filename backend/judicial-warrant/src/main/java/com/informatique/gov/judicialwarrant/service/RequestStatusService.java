package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;

public interface RequestStatusService extends Serializable {
	List<RequestStatusDto> getAll() throws JudicialWarrantException;

	RequestStatusDto getById(Byte id) throws JudicialWarrantException;

	public RequestStatusDto getByCode(String code) throws JudicialWarrantException;

}

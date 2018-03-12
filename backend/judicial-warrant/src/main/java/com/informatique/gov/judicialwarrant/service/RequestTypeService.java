package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;

public interface RequestTypeService extends Serializable{

	RequestTypeDto getByCode(String code) throws JudicialWarrantException;

	List<RequestTypeDto> getAll() throws JudicialWarrantException;

	List<RequestTypeDto> getActive() throws JudicialWarrantException;

	List<RequestTypeDto> getInActive() throws JudicialWarrantException;

}

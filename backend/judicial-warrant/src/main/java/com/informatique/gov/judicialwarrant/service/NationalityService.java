package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.NationalityDto;

public interface NationalityService extends Serializable{

	NationalityDto getByCode(String code) throws JudicialWarrantException;

	List<NationalityDto> getAll() throws JudicialWarrantException;


}

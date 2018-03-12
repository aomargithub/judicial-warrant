package com.informatique.gov.judicialwarrant.service;




import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.LocaleDto;

public interface LocaleService extends Serializable{

	LocaleDto getByCode(String code) throws JudicialWarrantException;

	List<LocaleDto> getAll() throws JudicialWarrantException;

	List<LocaleDto> getActive() throws JudicialWarrantException;

	List<LocaleDto> getInActive() throws JudicialWarrantException;
}

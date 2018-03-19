package com.informatique.gov.judicialwarrant.rest.handler;



import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.LocaleDto;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;

public interface LocaleHandler extends Serializable {

	ResponseEntity<LocaleDto> getByCode(String code) throws JudicialWarrantException;

	ResponseEntity<List<LocaleDto>> getByIsActive(Boolean isActive) throws JudicialWarrantException;

	ResponseEntity<List<LocaleDto>> getAll() throws JudicialWarrantException;

}

package com.informatique.gov.judicialwarrant.rest.handler;



import java.io.Serializable;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;

public interface LocaleHandler extends Serializable {

	RestResponse getByCode(String code) throws JudicialWarrantException;

	RestResponse getByIsActive(Boolean isActive) throws JudicialWarrantException;

	RestResponse getAll() throws JudicialWarrantException;

}
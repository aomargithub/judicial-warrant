package com.informatique.gov.judicialwarrant.rest.handler.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.NationalityDto;
import com.informatique.gov.judicialwarrant.rest.handler.NationalityHandler;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;
import com.informatique.gov.judicialwarrant.service.NationalityService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NationalityHandlerImpl implements NationalityHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6688098430686290417L;

	private NationalityService nationalityService;
	
	@Override
    public RestResponse getByCode(String code) throws JudicialWarrantException {
        RestResponse restResponse = null;
        try {
        	NationalityDto dto = nationalityService.getByCode(code);
            restResponse = new RestResponse(dto);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return restResponse;
    }
	
    @Override
    public RestResponse getAll() throws JudicialWarrantException {
        RestResponse restResponse = null;
        try {
            List<NationalityDto> dtos = nationalityService.getAll();
            restResponse = new RestResponse(dtos);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return restResponse;
    }
}

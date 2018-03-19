package com.informatique.gov.judicialwarrant.rest.handler.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestTypeHandler;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;
import com.informatique.gov.judicialwarrant.service.RequestTypeService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RequestTypeHandlerImpl implements RequestTypeHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6688098430686290417L;

	private RequestTypeService requestTypeService;
	
	@Override
    public RestResponse getByCode(String code) throws JudicialWarrantException {
        RestResponse restResponse = null;
        try {
        	RequestTypeDto dto = requestTypeService.getByCode(code);
            restResponse = new RestResponse(dto);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return restResponse;
    }
	
	@Override
	public RestResponse getByIsActive(Boolean isActive) throws JudicialWarrantException {
		
		RestResponse restResponse = null;
		try {
			List<RequestTypeDto> dtos = null;
			if(isActive) {
				dtos = requestTypeService.getActive();
			}else {
				dtos = requestTypeService.getInActive();
			}
			restResponse = new RestResponse(dtos);
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
            List<RequestTypeDto> dtos = requestTypeService.getAll();
            restResponse = new RestResponse(dtos);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return restResponse;
    }
}

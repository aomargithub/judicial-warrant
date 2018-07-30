package com.informatique.gov.judicialwarrant.rest.handler.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestTypeHandler;
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
    public ResponseEntity<RequestTypeDto> getByCode(String code) throws JudicialWarrantException {
		ResponseEntity<RequestTypeDto> response = null;
        try {
        	RequestTypeDto dto = requestTypeService.getByCode(code);
        	response = ResponseEntity.ok(dto);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return response;
    }
	
	/*@Override
	public ResponseEntity<List<RequestTypeDto>> getByIsActive(Boolean isActive) throws JudicialWarrantException {
		
		ResponseEntity<List<RequestTypeDto>> response = null;
		try {
			List<RequestTypeDto> dtos = null;
			if(isActive) {
				dtos = requestTypeService.getActive();
			}else {
				dtos = requestTypeService.getInActive();
			}
			response = ResponseEntity.ok(dtos);
		}catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
		return response;
	}*/
	
	@Override
	public ResponseEntity<List<RequestTypeDto>> getByIsActiveAndIsInternal(Boolean isActive, Boolean isInternal) throws JudicialWarrantException {
		
		ResponseEntity<List<RequestTypeDto>> response = null;
		try {
			List<RequestTypeDto> dtos = null;
			dtos = requestTypeService.getByIsActiveAndIsInternal(isActive, isInternal);			
			response = ResponseEntity.ok(dtos);
		}catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
		return response;
	}

    @Override
    public ResponseEntity<List<RequestTypeDto>> getAll() throws JudicialWarrantException {
    	ResponseEntity<List<RequestTypeDto>> response = null;
        try {
            List<RequestTypeDto> dtos = requestTypeService.getAll();
            response = ResponseEntity.ok(dtos);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return response;
    }
}

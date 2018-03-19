package com.informatique.gov.judicialwarrant.rest.handler.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.LocaleDto;
import com.informatique.gov.judicialwarrant.rest.handler.LocaleHandler;
import com.informatique.gov.judicialwarrant.service.LocaleService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LocaleHandlerImpl implements LocaleHandler {


    /**
	 * 
	 */
	private static final long serialVersionUID = 6641662457025852824L;
	
	private LocaleService localeService;
	
	@Override
    public ResponseEntity<LocaleDto> getByCode(String code) throws JudicialWarrantException {
		ResponseEntity<LocaleDto> response = null;
        try {
        	LocaleDto dto = localeService.getByCode(code);
        	response = ResponseEntity.ok(dto);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return response;
    }
	
	@Override
	public ResponseEntity<List<LocaleDto>> getByIsActive(Boolean isActive) throws JudicialWarrantException {
		
		ResponseEntity<List<LocaleDto>> response = null;
		try {
			List<LocaleDto> dtos = null;
			if(isActive) {
				dtos = localeService.getActive();
			}else {
				dtos = localeService.getInActive();
			}
			response = ResponseEntity.ok(dtos);
		}catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
		return response;
	}

    @Override
    public ResponseEntity<List<LocaleDto>> getAll() throws JudicialWarrantException {
    	ResponseEntity<List<LocaleDto>> response = null;
        try {
            List<LocaleDto> dtos = localeService.getAll();
            response = ResponseEntity.ok(dtos);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return response;
    }

}

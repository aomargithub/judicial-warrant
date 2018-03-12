package com.informatique.gov.judicialwarrant.rest.handler.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.LocaleDto;
import com.informatique.gov.judicialwarrant.rest.handler.LocaleHandler;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;
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
    public RestResponse getByCode(String code) throws JudicialWarrantException {
        RestResponse restResponse = null;
        try {
        	LocaleDto dto = localeService.getByCode(code);
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
			List<LocaleDto> dtos = null;
			if(isActive) {
				dtos = localeService.getActive();
			}else {
				dtos = localeService.getInActive();
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
            List<LocaleDto> dtos = localeService.getAll();
            restResponse = new RestResponse(dtos);
        }catch(JudicialWarrantException e){
            throw e;
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return restResponse;
    }

}

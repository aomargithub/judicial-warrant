package com.informatique.gov.judicialwarrant.rest.handler.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledHandler;
import com.informatique.gov.judicialwarrant.service.EntitledService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class EntitledHandlerImpl implements EntitledHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntitledService entitledService;
	
	@Override
	public ResponseEntity<Void> delete(Long id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			entitledService.deleteById(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

}

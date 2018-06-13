package com.informatique.gov.judicialwarrant.rest.handler.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledAttachmentHandler;
import com.informatique.gov.judicialwarrant.service.EntitledAttachmentService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class EntitledAttachmentHandlerImpl implements EntitledAttachmentHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntitledAttachmentService entitledAttachmentService;
	
	@Override
	public ResponseEntity<Void> delete(Long id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			entitledAttachmentService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

}

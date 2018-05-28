package com.informatique.gov.judicialwarrant.rest.handler.impl;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.rest.handler.EntitledAttachmentHandler;
import com.informatique.gov.judicialwarrant.service.EntitledAttachmentService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class EntitledAttachmentHandlerImple implements EntitledAttachmentHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntitledAttachmentService entitledAttachmentService;
	

}

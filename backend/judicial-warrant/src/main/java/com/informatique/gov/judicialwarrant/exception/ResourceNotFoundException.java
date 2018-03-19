package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

import lombok.Getter;

public class ResourceNotFoundException extends JudicialWarrantException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -334837118222909384L;

	@Getter
	private final Serializable id;
	public ResourceNotFoundException(Serializable id) {
		super(JudicialWarrantExceptionEnum.RESOURCE_NOT_FOUND_EXCEPTION.getCode(), JudicialWarrantExceptionEnum.RESOURCE_NOT_FOUND_EXCEPTION.getDescription(), JudicialWarrantExceptionEnum.RESOURCE_NOT_FOUND_EXCEPTION.getFixSuggestion());
		this.id = id;
	}

}

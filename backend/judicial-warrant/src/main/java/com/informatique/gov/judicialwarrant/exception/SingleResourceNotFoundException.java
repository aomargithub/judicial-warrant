package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

public class SingleResourceNotFoundException extends JudicialWarrantException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 483392221646603905L;

	public SingleResourceNotFoundException(String resourceClassName, Serializable id) {
		super(JudicialWarrantExceptionEnum.SINGLE_RESOURCE_NOT_FOUND_EXCEPTION.getCode(), String.format(JudicialWarrantExceptionEnum.SINGLE_RESOURCE_NOT_FOUND_EXCEPTION.getDescription(), resourceClassName, id), JudicialWarrantExceptionEnum.SINGLE_RESOURCE_NOT_FOUND_EXCEPTION.getFixSuggestion());
	}
}

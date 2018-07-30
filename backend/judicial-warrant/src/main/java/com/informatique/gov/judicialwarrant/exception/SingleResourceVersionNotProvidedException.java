package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

public class SingleResourceVersionNotProvidedException extends JudicialWarrantException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6307671693214221291L;

	public SingleResourceVersionNotProvidedException(String resourceClassName, Serializable id) {
		super(JudicialWarrantExceptionEnum.SINGLE_RESOURCE_VERSION_NOT_PROVIDED_EXCEPTION.getCode(),
				String.format(JudicialWarrantExceptionEnum.SINGLE_RESOURCE_VERSION_NOT_PROVIDED_EXCEPTION.getDescription(),
						resourceClassName, id),
				JudicialWarrantExceptionEnum.SINGLE_RESOURCE_VERSION_NOT_PROVIDED_EXCEPTION.getFixSuggestion());
	}
}

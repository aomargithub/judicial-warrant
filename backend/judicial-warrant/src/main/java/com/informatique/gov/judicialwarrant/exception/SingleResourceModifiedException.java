package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

public class SingleResourceModifiedException extends JudicialWarrantException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SingleResourceModifiedException(String resourceClassName, Serializable id, Short expectedVersion,
			Short realVersion) {
		super(JudicialWarrantExceptionEnum.SINGLE_RESOURCE_MODIFIED_EXCEPTION.getCode(),
				String.format(JudicialWarrantExceptionEnum.SINGLE_RESOURCE_MODIFIED_EXCEPTION.getDescription(),
						resourceClassName, id, realVersion, expectedVersion),
				JudicialWarrantExceptionEnum.SINGLE_RESOURCE_MODIFIED_EXCEPTION.getFixSuggestion());
	}
}

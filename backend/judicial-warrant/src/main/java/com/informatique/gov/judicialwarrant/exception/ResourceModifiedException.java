package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

import lombok.Getter;

public class ResourceModifiedException extends JudicialWarrantException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1507803968464885606L;

	@Getter
	private final Short realVersion;
	public ResourceModifiedException(Serializable id, Short expectedVersion, Short realVersion) {
		super(JudicialWarrantExceptionEnum.RESOURCE_MODIFIED_EXCEPTION.getCode(), JudicialWarrantExceptionEnum.RESOURCE_MODIFIED_EXCEPTION.getDescription(), JudicialWarrantExceptionEnum.RESOURCE_MODIFIED_EXCEPTION.getFixSuggestion());
		this.realVersion = realVersion;
	}

}

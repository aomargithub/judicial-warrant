package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

import lombok.Getter;

public class ResourceNotModifiedException extends JudicialWarrantException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6849614432000571777L;

	@Getter
	private final Short realVersion;
	public ResourceNotModifiedException(Serializable id, Short realVersion) {
		super(JudicialWarrantExceptionEnum.RESOURCE_NOT_MODIFIED_EXCEPTION.getCode(), JudicialWarrantExceptionEnum.RESOURCE_NOT_MODIFIED_EXCEPTION.getDescription(), JudicialWarrantExceptionEnum.RESOURCE_NOT_MODIFIED_EXCEPTION.getFixSuggestion());
		this.realVersion = realVersion;
	}

}

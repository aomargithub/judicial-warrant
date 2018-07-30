package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

import lombok.Getter;

public class ResourceNotModifiedException extends JudicialWarrantException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6849614432000571777L;

	@Getter
	private final Short currentVersion;
	public ResourceNotModifiedException(Serializable id, Short currentVersion) {
		super(JudicialWarrantExceptionEnum.RESOURCE_NOT_MODIFIED_EXCEPTION);
		this.currentVersion = currentVersion;
	}

}

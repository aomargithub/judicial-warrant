package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

import lombok.Getter;

public class ResourceModifiedException extends JudicialWarrantException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1507803968464885606L;

	@Getter
	private final Serializable id;
	@Getter
	private final Short targetVersion;
	@Getter
	private final Short currentVersion;
	public ResourceModifiedException(Serializable id, Short targetVersion, Short currentVersion) {
		super(JudicialWarrantExceptionEnum.RESOURCE_MODIFIED_EXCEPTION);
		this.currentVersion = currentVersion;
		this.id = id;
		this.targetVersion = targetVersion;
	}

}

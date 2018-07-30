package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

import lombok.Getter;

public class PreConditionRequiredException extends JudicialWarrantException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6610574449409519179L;

	@Getter
	private final Serializable id;
	public PreConditionRequiredException(Serializable id) {
		super(JudicialWarrantExceptionEnum.PRE_CONDITION_REQUIRED);
		this.id = id;
	}

}

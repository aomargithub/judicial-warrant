package com.informatique.gov.judicialwarrant.exception;

import java.io.Serializable;

public class JudicialwarrantError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2747213746528012231L;

	private String errorCode;
	private String errorDescription;
	private String fixSuggestion;
	private Object target;
	private Object errorArguments;

	public JudicialwarrantError(Object target, JudicialWarrantExceptionEnum sakErrorEnum, Object errorArguments) {
		this.target = target;
		this.errorCode = sakErrorEnum.getCode();
		this.errorDescription = sakErrorEnum.getDescription();
		this.fixSuggestion = sakErrorEnum.getFixSuggestion();
		this.errorArguments = errorArguments;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public String getFixSuggestion() {
		return fixSuggestion;
	}

	public Object getTarget() {
		return target;
	}

	public Object getErrorArguments() {
		return errorArguments;
	}

}

package com.informatique.gov.judicialwarrant.exception;

public class WrongOldPasswordException extends JudicialWarrantException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongOldPasswordException() {
		super(JudicialWarrantExceptionEnum.WRONG_OLD_PASSWORD_EXCEPTION);
	}

}

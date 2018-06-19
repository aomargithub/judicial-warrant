package com.informatique.gov.judicialwarrant.exception;

public class IncompleteAttachmentsException extends JudicialWarrantException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncompleteAttachmentsException() {
		super(JudicialWarrantExceptionEnum.INCOMPLETE_ATTACHMENTS);
	}

}

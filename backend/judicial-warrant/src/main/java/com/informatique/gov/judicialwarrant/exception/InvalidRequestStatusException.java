package com.informatique.gov.judicialwarrant.exception;

import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

import lombok.Getter;

@Getter
public class InvalidRequestStatusException extends JudicialWarrantException {

	private static final long serialVersionUID = 6920349127579296927L;
	
	

	public InvalidRequestStatusException(String requestSerial, RequestStatusEnum requestStatusEnum) {
		super(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getCode(),
				String.format(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getDescription(), requestStatusEnum.getCode(), requestSerial),
				JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getFixSuggestion());
	}

	public InvalidRequestStatusException(String requestSerial, RequestInternalStatusEnum requestInternalStatusEnum) {
		super(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getCode(),
				String.format(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getDescription(), requestInternalStatusEnum.getCode(), requestSerial),
				JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getFixSuggestion());
	}
	
}

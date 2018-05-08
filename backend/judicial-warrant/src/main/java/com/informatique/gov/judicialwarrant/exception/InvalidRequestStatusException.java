package com.informatique.gov.judicialwarrant.exception;

import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

import lombok.Getter;

@Getter
public class InvalidRequestStatusException extends JudicialWarrantException {

	private static final long serialVersionUID = 6920349127579296927L;
	
	private final String requestSerial;
	private final RequestStatusEnum requestStatusEnum;
	private final RequestInternalStatusEnum requestInternalStatusEnum;

	public InvalidRequestStatusException(String requestSerial, RequestStatusEnum requestStatusEnum) {
		super(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getCode(),
				JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getDescription(),
				JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getFixSuggestion());
		this.requestSerial = requestSerial;
		this.requestStatusEnum = requestStatusEnum;
		this.requestInternalStatusEnum = null;
	}

	public InvalidRequestStatusException(String requestSerial, RequestInternalStatusEnum requestInternalStatusEnum) {
		super(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getCode(),
				JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getDescription(),
				JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getFixSuggestion());
		this.requestSerial = requestSerial;
		this.requestStatusEnum = null;
		this.requestInternalStatusEnum = requestInternalStatusEnum;
	}
	
}

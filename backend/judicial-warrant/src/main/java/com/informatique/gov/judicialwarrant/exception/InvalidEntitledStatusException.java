package com.informatique.gov.judicialwarrant.exception;

import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;

import lombok.Getter;

@Getter
public class InvalidEntitledStatusException extends JudicialWarrantException {

	private static final long serialVersionUID = 6920349127579296927L;
	
	

	public InvalidEntitledStatusException(Long id, EntitledStatusEnum entitledStatusEnum) {
		super(JudicialWarrantExceptionEnum.INVALID_ENTITLED_STATUS_EXCEPTION.getCode(),
				String.format(JudicialWarrantExceptionEnum.INVALID_ENTITLED_STATUS_EXCEPTION.getDescription(), entitledStatusEnum.getCode()),
				JudicialWarrantExceptionEnum.INVALID_ENTITLED_STATUS_EXCEPTION.getFixSuggestion());
	}
	
}

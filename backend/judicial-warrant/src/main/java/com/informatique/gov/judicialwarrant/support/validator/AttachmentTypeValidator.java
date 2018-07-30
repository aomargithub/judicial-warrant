package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;

@Component
public class AttachmentTypeValidator implements Validator, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7947006825720467090L;

	@Override
	public boolean supports(Class<?> clazz) {
		return AttachmentTypeDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		AttachmentTypeDto attachmentTypeDto = (AttachmentTypeDto) object;
		if(attachmentTypeDto.getListOrder() == null) {
			errors.reject(JudicialWarrantExceptionEnum.EXCEPTION_IN_VALIDATION.getCode());
		}
	}

}

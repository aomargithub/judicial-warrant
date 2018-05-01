package com.informatique.gov.judicialwarrant.support.validations;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;
@Component
public class RoleDtoValidator implements Validator,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917474366863097083L;

	/**
	 * 
	 */


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return RoleDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		RoleDto roleDto=(RoleDto) target;
		
//		if(roleDto==null) {
//			errors.rejectValue("RoleDto Nullability", "RoleDto Nullability", null, null);
//			return;
//		}
//		
//		else if(roleDto.getArabicName().length()>5) {
//			errors.rejectValue("arabicName", "should be 5 chars or less", null, null);
//			return;
//		}
		
		
	}

}

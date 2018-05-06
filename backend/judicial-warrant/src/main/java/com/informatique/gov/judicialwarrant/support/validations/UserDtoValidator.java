package com.informatique.gov.judicialwarrant.support.validations;

import java.io.Serializable;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.persistence.repository.UserRepository;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserDtoValidator implements Validator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8664319447831307551L;
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDto userDto = (UserDto) target;
		if (userDto == null) {
			errors.rejectValue(null, JudicialWarrantExceptionEnum.USER_NULL.getCode(), null, null);
			return;
		}

		

		User userExistEntity = null;
		userExistEntity = userRepository.findByEmailAddress(userDto.getEmailAddress(),userDto.getId());

		if (userExistEntity != null) {
			errors.rejectValue("emailAddress", JudicialWarrantExceptionEnum.USER_EMAIL_ADDRESS_ALEARDY_EXISTS.getCode(), null, null);
			return;

		}

		userExistEntity = userRepository.findByLoginName(userDto.getLoginName(),userDto.getId());

		if (userExistEntity != null) {
			errors.rejectValue("loginName",JudicialWarrantExceptionEnum.USER_LOGIN_NAME_EXISTS.getCode() , null, null);
			return;

		}
		userExistEntity = userRepository.findByCivilId(userDto.getCivilId(),userDto.getId());

		if (userExistEntity != null) {
			errors.rejectValue("civilId",JudicialWarrantExceptionEnum.USER_CIVIL_ID_EXIST.getCode() , null, null);
			return;

		}
		

		if (userDto.getOrganizationUnit().getId() == null) {
			errors.rejectValue("organizationUnit.id",JudicialWarrantExceptionEnum.USER_ORGANIZATION_UNIT_ID_NULL.getCode() , null, null);
			return;

		}
		

		if (userDto.getRole().getId() == null) {
			errors.rejectValue("role.id", JudicialWarrantExceptionEnum.USER_ROLE_ID_NULL.getCode() , null, null);
			return;

		}
		
		userExistEntity = userRepository.findByMobileNumber1(userDto.getMobileNumber1(),userDto.getId());

		if (userExistEntity != null) {
			errors.rejectValue("mobileNumber1",JudicialWarrantExceptionEnum.MOBILE_NUMBER1_EXIST.getCode() , null, null);
			return;

		}

	}

}

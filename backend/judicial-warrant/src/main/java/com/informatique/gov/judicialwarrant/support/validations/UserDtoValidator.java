package com.informatique.gov.judicialwarrant.support.validations;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.persistence.repository.UserRepository;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.support.dataenum.UserTypeEnum;
import com.informatique.gov.judicialwarrant.support.ldap.LdapService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserDtoValidator implements Validator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8664319447831307551L;
	private UserRepository userRepository;
	private LdapService ldapService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		try {
			UserDto userDto = (UserDto) target;
			if (userDto == null) {
				errors.rejectValue(null, JudicialWarrantExceptionEnum.USER_NULL.getCode(), null, null);
				return;
			}

			if (userDto.getUserType() != null) {
				if (userDto.getUserType().getCode().equalsIgnoreCase(UserTypeEnum.INTERNAL.getCode())) {
					if (!ldapService.checkUserExists(userDto.getLoginName())) {
						errors.rejectValue("loginName",
								JudicialWarrantExceptionEnum.USER_LOGIN_NAME_NOT_FOUND_LDAP.getCode(), null, null);
						return;
					}
				}
			}

			Integer id = null;
			if (userDto.getId() != null) {
				id = userRepository.findIdByEmailAddressAndNotEqualId(userDto.getEmailAddress(), userDto.getId());
			} else {
				id = userRepository.findIdByEmailAddress(userDto.getEmailAddress());
			}

			if (id != null) {
				errors.rejectValue("emailAddress",
						JudicialWarrantExceptionEnum.USER_EMAIL_ADDRESS_ALEARDY_EXISTS.getCode(), null, null);
				return;

			}

			if (userDto.getId() != null) {
				id = userRepository.findIdByLoginNameAndNotEqualId(userDto.getLoginName(), userDto.getId());

			} else {
				id = userRepository.findIdByLoginName(userDto.getLoginName());

			}

			if (id != null) {
				errors.rejectValue("loginName", JudicialWarrantExceptionEnum.USER_LOGIN_NAME_EXISTS.getCode(), null,
						null);
				return;

			}

			if (userDto.getId() != null) {
				id = userRepository.findIdByCivilIdAndNotEqualId(userDto.getCivilId(), userDto.getId());
			} else {
				id = userRepository.findIdByCivilId(userDto.getCivilId());
			}

			if (id != null) {
				errors.rejectValue("civilId", JudicialWarrantExceptionEnum.USER_CIVIL_ID_EXIST.getCode(), null, null);
				return;

			}

			if (userDto.getOrganizationUnit().getId() == null) {
				errors.rejectValue("organizationUnit.id",
						JudicialWarrantExceptionEnum.USER_ORGANIZATION_UNIT_ID_NULL.getCode(), null, null);
				return;

			}

			if (userDto.getRole().getId() == null) {
				errors.rejectValue("role.id", JudicialWarrantExceptionEnum.USER_ROLE_ID_NULL.getCode(), null, null);
				return;

			}

			if (userDto.getId() != null) {
				id = userRepository.findIdByMobileNumber1AndNotEqualId(userDto.getMobileNumber1(), userDto.getId());

			} else {

				id = userRepository.findIdByMobileNumber1(userDto.getMobileNumber1());

			}

			if (id != null) {
				errors.rejectValue("mobileNumber1", JudicialWarrantExceptionEnum.MOBILE_NUMBER1_EXIST.getCode(), null,
						null);
				return;
			}
		} catch (Exception e) {

		}

	}

}

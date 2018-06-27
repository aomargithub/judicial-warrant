package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.persistence.repository.CapacityDelegationRepository;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.service.SecurityService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledRegistrationDtoValidator implements Validator, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2737829559590624524L;
	private CapacityDelegationRepository capacityDelegationRepository;
	private SecurityService securityService;

	@Override
	public boolean supports(Class<?> clazz) {
		return EntitledRegistrationDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EntitledRegistrationDto entitledRegistrationDto = (EntitledRegistrationDto) target;
		CapacityDelegation capacityDelegation = capacityDelegationRepository.findByRequestSerial(entitledRegistrationDto.getCapacityDelegation().getRequest().getSerial());
		try {
			// check if capacityDelegation request submitted before add any entitled based it
			if(!capacityDelegation.getRequest().getCurrentInternalStatus().getCode().equals(RequestInternalStatusEnum.ISSUED.getCode())) {
				errors.reject(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getCode(), JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getDescription());
			}
			// check that user add right capacityDelagation for organizationUnit
			OrganizationUnitDto userOrganizationUnitDto = securityService.getUserDetails().getOrganizationUnit();
			OrganizationUnit requestOrganizationUnit = capacityDelegation.getRequest().getOrganizationUnit();
			if(!userOrganizationUnitDto.getId().equals(requestOrganizationUnit.getId())) {
				errors.reject(JudicialWarrantExceptionEnum.CAPACITY_DELEGATION_NOT_ALLOWED.getCode(), JudicialWarrantExceptionEnum.CAPACITY_DELEGATION_NOT_ALLOWED.getDescription());
			}
		} catch (JudicialWarrantException e) {
			e.printStackTrace();
		}
	}

}

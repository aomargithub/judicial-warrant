package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.request.EntitledRegistrationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.service.EntitledAttachmentService;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledAttachmentsValidator implements Validator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7947006825720467090L;
	private RequestTypeAttachmentTypeService requestTypeAttachmentTypeService;
	private EntitledAttachmentService entitledAttachmentService;

	@Override
	public boolean supports(Class<?> clazz) {
		return EntitledRegistrationChangeStatusRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		EntitledRegistrationDto entitledRegistrationDto = ((EntitledRegistrationChangeStatusRequest) object)
				.getEntitledRegistration();

		try {
			List<RequestTypeAttachmentTypeDto> requestTypeAttachmentTypeDtos = requestTypeAttachmentTypeService
					.getByRequestTypeCodeAndAttachmentTypeIsEntitledAttachment(
							RequestTypeEnum.ENTITLED_REGISTRATION.getCode(), true);
			if (requestTypeAttachmentTypeDtos != null) {
				for (EntitledDto entitledDto : entitledRegistrationDto.getEntitled()) {
					List<EntitledAttachmentDto> entitledAttachmentDtos = entitledAttachmentService
							.getByEntitledId(entitledDto.getId());
					if ((entitledAttachmentDtos == null || entitledAttachmentDtos.isEmpty())) {
						errors.reject(JudicialWarrantExceptionEnum.EXCEPTION_IN_VALIDATION.getCode());
					} else {
						for (RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto : requestTypeAttachmentTypeDtos) {
							boolean match = false;
							for (EntitledAttachmentDto entitledAttachmentDto : entitledAttachmentDtos) {
								if (requestTypeAttachmentTypeDto.getAttachmentType().getId()
										.equals(entitledAttachmentDto.getAttachmentType().getId())) {
									match = true;
									break;
								}
							}
							if (!match) {
								errors.reject(JudicialWarrantExceptionEnum.EXCEPTION_IN_VALIDATION.getCode());
							}
						}
					}
				}
			}
		} catch (JudicialWarrantException e) {
			e.printStackTrace();
		}

	}

}
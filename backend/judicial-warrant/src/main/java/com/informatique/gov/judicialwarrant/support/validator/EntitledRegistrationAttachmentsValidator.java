package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.EntitledRegistration;
import com.informatique.gov.judicialwarrant.exception.IncompleteAttachmentsException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.service.EntitledService;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledRegistrationAttachmentsValidator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7947006825720467090L;
	private EntitledAttachmentsValidator entitledAttachmentsValidator;
	private RequestTypeAttachmentTypeService requestTypeAttachmentTypeService;
	private RequestAttachmentService requestAttachmentService;
	private EntitledService entitledService;

	public void validate(EntitledRegistration entitledRegistration) {
		try {
			List<RequestTypeAttachmentTypeDto> requestTypeAttachmentTypeDtos = requestTypeAttachmentTypeService
					.getByRequestTypeCodeAndAttachmentTypeIsEntitledAttachment(
							RequestTypeEnum.ENTITLED_REGISTRATION.getCode(), false);
			List<RequestAttachmentDto> requestAttachmentDtos = requestAttachmentService
					.getAllByRequestSerial(entitledRegistration.getRequest().getSerial());
			if (requestTypeAttachmentTypeDtos != null) {
				if ((requestAttachmentDtos == null || requestAttachmentDtos.isEmpty())) {
					throw new IncompleteAttachmentsException();
				} else {
					for (RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto : requestTypeAttachmentTypeDtos) {
						boolean match = false;
						for (RequestAttachmentDto requestAttachmentDto : requestAttachmentDtos) {
							if (requestTypeAttachmentTypeDto.getAttachmentType().getId()
									.equals(requestAttachmentDto.getAttachmentType().getId())) {
								match = true;
								break;
							}
						}
						if (!match) {
							throw new IncompleteAttachmentsException();
						}
					}
				}
			}

			// call validator for entitleds Attachments
			entitledAttachmentsValidator.validate(entitledService.getAllByEntitledRegistrationSerial(entitledRegistration.getRequest().getSerial()));

		} catch (JudicialWarrantException e) {
			e.printStackTrace();
		}

	}

}

package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.exception.IncompleteAttachmentsException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;
import com.informatique.gov.judicialwarrant.service.RequestTypeService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CapacityDelegationAttachmentsValidator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2737829559590624524L;
	private RequestTypeAttachmentTypeService requestTypeAttachmentTypeService;
	private RequestTypeService requestTypeService;
	private RequestAttachmentService requestAttachmentService;

	public void validate(CapacityDelegation capacityDelegation) throws JudicialWarrantException {
		String serial = capacityDelegation.getRequest().getSerial();
		try {
			RequestTypeDto requestTypeDto = requestTypeService.getByCode(RequestTypeEnum.CAPACITY_DELEGATION.getCode());
			List<RequestTypeAttachmentTypeDto> requestTypeAttachmentTypeDtos = requestTypeAttachmentTypeService
					.getByRequestTypeId(requestTypeDto.getId());

			List<RequestAttachmentDto> requestAttachmentDtos = requestAttachmentService.getAllByRequestSerial(serial);

			if (requestTypeAttachmentTypeDtos != null) {
				for (RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto : requestTypeAttachmentTypeDtos) {
					AttachmentTypeDto attachmentTypeDto = requestTypeAttachmentTypeDto.getAttachmentType();
					boolean found = false;
					if ((requestAttachmentDtos == null || requestAttachmentDtos.isEmpty())) {
						throw new IncompleteAttachmentsException();
					} else {
						for (RequestAttachmentDto requestAttachmentDto : requestAttachmentDtos) {
							if (attachmentTypeDto.getId().equals(requestAttachmentDto.getAttachmentType().getId())) {
								found = true;
								break;
							}
						}
						if (!found) {
							throw new IncompleteAttachmentsException();
						}
					}

				}
			}

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

	}

}

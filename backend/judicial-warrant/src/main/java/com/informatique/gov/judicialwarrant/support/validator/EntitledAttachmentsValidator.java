package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.IncompleteAttachmentsException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.service.EntitledAttachmentService;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledAttachmentsValidator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7947006825720467090L;
	private RequestTypeAttachmentTypeService requestTypeAttachmentTypeService;
	private EntitledAttachmentService entitledAttachmentService;

	public void validate(List<EntitledDto> entitledDtos) throws JudicialWarrantException {

		try {
			List<RequestTypeAttachmentTypeDto> requestTypeAttachmentTypeDtos = requestTypeAttachmentTypeService
					.getByRequestTypeCodeAndAttachmentTypeIsEntitledAttachment(
							RequestTypeEnum.ENTITLED_REGISTRATION.getCode(), true);
			if (requestTypeAttachmentTypeDtos != null) {
				for (EntitledDto entitledDto : entitledDtos) {
					List<EntitledAttachmentDto> entitledAttachmentDtos = entitledAttachmentService
							.getByEntitledId(entitledDto.getId());
					if ((entitledAttachmentDtos == null || entitledAttachmentDtos.isEmpty())) {
						throw new IncompleteAttachmentsException();
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
								new IncompleteAttachmentsException();
							}
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

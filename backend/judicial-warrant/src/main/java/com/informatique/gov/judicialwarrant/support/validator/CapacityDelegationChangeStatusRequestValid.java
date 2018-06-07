package com.informatique.gov.judicialwarrant.support.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;
import com.informatique.gov.judicialwarrant.service.RequestTypeService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
@AllArgsConstructor
@Component
public class CapacityDelegationChangeStatusRequestValid implements ConstraintValidator<CapacityDelegationChangeStatusRequestV, Object>{
	private RequestTypeAttachmentTypeService requestTypeAttachmentTypeService;
	private RequestTypeService requestTypeService;
	private RequestAttachmentService requestAttachmentService;
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
	//	CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest=(CapacityDelegationChangeStatusRequest)value;
		CapacityDelegationDto capacityDelegationDto=(CapacityDelegationDto)value;
		String serial=capacityDelegationDto.getRequest().getSerial();
		try {
			RequestTypeDto requestTypeDto = requestTypeService.getByCode(RequestTypeEnum.CAPACITY_DELEGATION.getCode());
			List<RequestTypeAttachmentTypeDto> requestTypeAttachmentTypeDtos = requestTypeAttachmentTypeService.getByRequestTypeId(requestTypeDto.getId());
		
			List<RequestAttachmentDto> requestAttachmentDtos = requestAttachmentService.getAllByRequestSerial(serial);
			
			for (RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto : requestTypeAttachmentTypeDtos) {
				AttachmentTypeDto attachmentTypeDto = requestTypeAttachmentTypeDto.getAttachmentType();
				boolean found = false;
				for(RequestAttachmentDto requestAttachmentDto : requestAttachmentDtos) {
					if(attachmentTypeDto.getId().equals(requestAttachmentDto.getAttachmentType().getId())) {
						found = true;
						break;
					}
				}
				if(!found) {
					return false;
					

				}
				
				
				
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JudicialWarrantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	
	
}

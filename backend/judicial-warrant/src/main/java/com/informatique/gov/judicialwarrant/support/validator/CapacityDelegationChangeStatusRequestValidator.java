package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;
import com.informatique.gov.judicialwarrant.service.RequestTypeService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

@Component

public class CapacityDelegationChangeStatusRequestValidator implements Validator, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2737829559590624524L;
	private RequestTypeAttachmentTypeService requestTypeAttachmentTypeService;
	private RequestTypeService requestTypeService;
	private RequestAttachmentService requestAttachmentService;
	
	public CapacityDelegationChangeStatusRequestValidator(RequestTypeAttachmentTypeService requestTypeAttachmentTypeService, RequestTypeService requestTypeService, RequestAttachmentService requestAttachmentService) {
		this.requestTypeAttachmentTypeService = requestTypeAttachmentTypeService;
		this.requestTypeService = requestTypeService;
		this.requestAttachmentService = requestAttachmentService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CapacityDelegationChangeStatusRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest=(CapacityDelegationChangeStatusRequest)target;
//		String serial=capacityDelegationChangeStatusRequest.getCapacityDelegation().getRequest().getSerial();
//		try {
//			RequestTypeDto requestTypeDto = requestTypeService.getByCode(RequestTypeEnum.CAPACITY_DELEGATION.getCode());
//			List<RequestTypeAttachmentTypeDto> requestTypeAttachmentTypeDtos = requestTypeAttachmentTypeService.getByRequestTypeId(requestTypeDto.getId());
//		
//			List<RequestAttachmentDto> requestAttachmentDtos = requestAttachmentService.getAllByRequestSerial(serial);
//			
//			for (RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto : requestTypeAttachmentTypeDtos) {
//				AttachmentTypeDto attachmentTypeDto = requestTypeAttachmentTypeDto.getAttachmentType();
//				boolean found = false;
//				for(RequestAttachmentDto requestAttachmentDto : requestAttachmentDtos) {
//					if(attachmentTypeDto.getId().equals(requestAttachmentDto.getAttachmentType().getId())) {
//						found = true;
//						break;
//					}
//				}
//				if(!found) {
//					errors.reject(JudicialWarrantExceptionEnum.EXCEPTION_IN_VALIDATION.getCode());
//
//				}
//				
//				
//				
//			}
//			
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JudicialWarrantException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}

}

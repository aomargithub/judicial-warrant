package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.RequestStatus;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class RequestStatusMapper extends AbstractModelMapper<RequestStatus, RequestStatusDto, Byte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7555189809233676907L;

	@Override
	public RequestStatusDto toDto(RequestStatus entity) {
		RequestStatusDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new RequestStatusDto();
			dto.setArabicName(entity.getArabicName());
			dto.setCode(entity.getEnglishName());
			dto.setEnglishName(entity.getEnglishName());
			dto.setId(entity.getId());
		}
		
		return dto;
	}

	@Override
	protected RequestStatus toEntity(RequestStatusDto dto, boolean nullId) {
		RequestStatus entity = null;
		
		if(isConvertable(dto)) {
			entity = new RequestStatus();
			entity.setArabicName(dto.getArabicName());
			entity.setCode(dto.getEnglishName());
			entity.setEnglishName(dto.getEnglishName());
			entity.setId(dto.getId());
		}
		
		return entity;
	}

}

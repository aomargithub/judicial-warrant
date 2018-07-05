package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.RequestInternalStatus;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;


@Component
public class RequestInternalStatusMapper extends AbstractModelMapper<RequestInternalStatus, RequestStatusDto, Short> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7555189809233676907L;

	@Override
	public RequestStatusDto toDto(RequestInternalStatus entity) {
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
	protected RequestInternalStatus toEntity(RequestStatusDto dto, boolean nullId) {
		RequestInternalStatus entity = null;
		
		if(isConvertable(dto)) {
			entity = new RequestInternalStatus();
			entity.setArabicName(dto.getArabicName());
			entity.setCode(dto.getEnglishName());
			entity.setEnglishName(dto.getEnglishName());
			entity.setId(dto.getId());
		}
		
		return entity;
	}

}

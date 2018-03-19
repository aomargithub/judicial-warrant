package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;

@Component
public class RequestTypeMapper extends AbstractModelMapper<RequestType, RequestTypeDto, Byte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -843117391908130543L;

	@Override
	public RequestTypeDto toDto(RequestType entity) {
		RequestTypeDto dto = null;
		
		if(isConvertable(entity)){
            dto = new RequestTypeDto();
            dto.setId(entity.getId());
            dto.setArabicName(entity.getArabicName());
            dto.setEnglishName(entity.getEnglishName());
            dto.setCode(entity.getCode());
            dto.setIsActive(entity.getIsActive());
        }
		
		return dto;
	}

	@Override
	protected RequestType toEntity(RequestTypeDto dto, boolean nullId) {
		RequestType entity = null;
		
		if(isConvertable(dto)){
            entity = new RequestType();
            entity.setId(nullId ? null : dto.getId());
            entity.setArabicName(dto.getArabicName());
            entity.setEnglishName(dto.getEnglishName());
            entity.setCode(dto.getCode());
            entity.setIsActive(dto.getIsActive());
        }
		
		return entity;
	}

}

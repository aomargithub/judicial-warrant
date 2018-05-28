package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.EntitledStatus;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;

@Component
public class EntitledStatusMapper extends AbstractModelMapper<EntitledStatus, EntitledStatusDto, Byte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -782343511755386476L;

	@Override
	public EntitledStatusDto toDto(EntitledStatus entity) {
		EntitledStatusDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new EntitledStatusDto();
			dto.setArabicName(entity.getArabicName());
			dto.setCode(entity.getCode());
			dto.setEnglishName(entity.getEnglishName());
			dto.setId(entity.getId());
		}
		
		return dto;
	}

	@Override
	protected EntitledStatus toEntity(EntitledStatusDto dto, boolean nullId) {
		EntitledStatus entity = null;
		
		if(isConvertable(dto)) {
			entity = new EntitledStatus();
			entity.setId(nullId? null :dto.getId());
			entity.setArabicName(dto.getArabicName());
			entity.setCode(dto.getCode());
			entity.setEnglishName(dto.getEnglishName());
		}
		
		return entity;
	}

}

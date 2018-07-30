package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.Nationality;
import com.informatique.gov.judicialwarrant.rest.dto.NationalityDto;

@Component
public class NationalityMapper extends AbstractModelMapper<Nationality, NationalityDto, Short>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -843117391908130543L;

	@Override
	public NationalityDto toDto(Nationality entity) {
		NationalityDto dto = null;
		
		if(isConvertable(entity)){
            dto = new NationalityDto();
            dto.setId(entity.getId());
            dto.setArabicName(entity.getArabicName());
            dto.setEnglishName(entity.getEnglishName());
            dto.setCode(entity.getCode());
            dto.setIso(entity.getIso());
        }
		
		return dto;
	}

	@Override
	protected Nationality toEntity(NationalityDto dto, boolean nullId) {
		Nationality entity = null;
		
		if(isConvertable(dto)){
            entity = new Nationality();
            entity.setId(nullId ? null : dto.getId());
            entity.setArabicName(dto.getArabicName());
            entity.setEnglishName(dto.getEnglishName());
            entity.setCode(dto.getCode());
            entity.setIso(dto.getIso());
        }
		
		return entity;
	}

}

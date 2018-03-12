package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.Locale;
import com.informatique.gov.judicialwarrant.rest.dto.LocaleDto;

@Component
public class LocaleMapper extends AbstractModelMapper<Locale, LocaleDto, Byte>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1097082497936548116L;

	@Override
    protected Locale toEntity(LocaleDto dto, boolean nullId) {
        Locale entity = null;

        if(isConvertable(dto)){
            entity = new Locale();
            entity.setId(nullId ? null : dto.getId());
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            entity.setIsActive(dto.getIsActive());
        }

        return entity;
    }

    @Override
    public LocaleDto toDto(Locale entity) {
        LocaleDto dto = null;

        if(isConvertable(entity)){
            dto = new LocaleDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCode(entity.getCode());
            dto.setIsActive(entity.getIsActive());
        }

        return dto;
    }
}

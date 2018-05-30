package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class AttachmentTypeMapper extends AbstractModelMapper<AttachmentType, AttachmentTypeDto, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7965596748827389580L;
	
	@Override
	public AttachmentTypeDto toDto(AttachmentType entity) {
		AttachmentTypeDto dto = null;

        if(isConvertable(entity)){
            dto = new AttachmentTypeDto();
            dto.setId(entity.getId());
            dto.setArabicName(entity.getArabicName());
            dto.setEnglishName(entity.getEnglishName());
            dto.setIsEntitledAttachment(entity.getIsEntitledAttachment());
            dto.setListOrder(entity.getListOrder());
            dto.setIsActive(entity.getIsActive());
            dto.setVersion(entity.getVersion());
            dto.setIsMandatory(entity.getIsMandatory());
        }

        return dto;
	}

	@Override
	protected AttachmentType toEntity(AttachmentTypeDto dto, boolean nullId) {
		AttachmentType entity = null;

        if(isConvertable(dto)){
        	entity = new AttachmentType();
        	entity.setId(nullId? null : dto.getId());
        	entity.setArabicName(dto.getArabicName());
        	entity.setEnglishName(dto.getEnglishName());
        	entity.setIsEntitledAttachment(dto.getIsEntitledAttachment());
        	entity.setListOrder(dto.getListOrder());
        	entity.setIsActive(dto.getIsActive());
        	entity.setVersion(dto.getVersion());
        	entity.setIsMandatory(dto.getIsMandatory());
        }

        return entity;
	}

}

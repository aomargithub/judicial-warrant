package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.persistence.repository.AttachmentTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class AttachmentTypeMapper extends AbstractModelMapper<AttachmentType, AttachmentTypeDto, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7965596748827389580L;

	private AttachmentTypeRepository attachmentTypeRepository;
	
	@Override
	public AttachmentTypeDto toDto(AttachmentType entity) {
		AttachmentTypeDto dto = null;

        if(isConvertable(entity)){
            dto = new AttachmentTypeDto();
            dto.setId(entity.getId());
            dto.setArabicName(entity.getArabicName());
            dto.setEnglishName(entity.getEnglishName());
            dto.setIsCandidateAttachment(entity.getIsCandidateAttachment());
            dto.setListOrder(entity.getListOrder());
            dto.setIsActive(entity.getIsActive());
        }

        return dto;
	}

	@Override
	protected AttachmentType toEntity(AttachmentTypeDto dto, boolean nullId) {
		AttachmentType entity = null;

        if(isConvertable(dto)){
        	if(dto.getId() != null) {
        		entity = attachmentTypeRepository.getOne(dto.getId());
        	} else {
        		entity = new AttachmentType();
        	}
        	entity.setId(nullId? null : dto.getId());
        	entity.setArabicName(dto.getArabicName());
        	entity.setEnglishName(dto.getEnglishName());
        	entity.setIsCandidateAttachment(dto.getIsCandidateAttachment());
        	entity.setListOrder(dto.getListOrder());
        	entity.setIsActive(dto.getIsActive());
        }

        return entity;
	}

}

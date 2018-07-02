package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledAttachment;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class EntitledAttachmentMapper extends AbstractModelMapper<EntitledAttachment, EntitledAttachmentDto, Long> {

	private ModelMapper<AttachmentType, AttachmentTypeDto, Long> attachmentTypeMapper;
	private ModelMapper<Entitled, EntitledDto, Long> entitledMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public EntitledAttachmentDto toDto(EntitledAttachment entity) {
		EntitledAttachmentDto dto = null;

		if (isConvertable(entity)) {
			dto = new EntitledAttachmentDto();
			dto.setId(entity.getId());
			dto.setUcmDocumentId(entity.getUcmDocumentId());
			dto.setAttachmentType(attachmentTypeMapper.toDto(entity.getAttachmentType()));
			dto.setFileName(entity.getFileName());
			dto.setVersion(entity.getVersion());
			dto.setEntitled(entitledMapper.toDto(entity.getEntitled()));
		}
		return dto;
	}

	@Override
	protected EntitledAttachment toEntity(EntitledAttachmentDto dto, boolean nullId) {
		EntitledAttachment entity = null;

		if (isConvertable(dto)) {
			entity = new EntitledAttachment();
			entity.setAttachmentType(attachmentTypeMapper.toEntity(dto.getAttachmentType()));
			entity.setEntitled(entitledMapper.toEntity(dto.getEntitled()));
			entity.setUcmDocumentId(dto.getUcmDocumentId());
			entity.setFileName(dto.getFileName());
			entity.setId(dto.getId());
			entity.setVersion(dto.getVersion());
		}

		return entity;
	}

}

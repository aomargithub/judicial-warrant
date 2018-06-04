package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.domain.RequestTypeAttachmentType;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;

@Component
public class RequestTypeAttachmentTypeMapper extends AbstractModelMapper<RequestTypeAttachmentType, RequestTypeAttachmentTypeDto, Short>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1050479696391169687L;
	
	@Autowired
	private ModelMapper<RequestType, RequestTypeDto, Byte> requestTypeMapper;
	
	@Autowired
	private ModelMapper<AttachmentType, AttachmentTypeDto, Long > attachmentTypeMapper;

	@Override
	public RequestTypeAttachmentTypeDto toDto(RequestTypeAttachmentType entity) {
		RequestTypeAttachmentTypeDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new RequestTypeAttachmentTypeDto();
			dto.setId(entity.getId());
			dto.setIsActive(entity.getIsActive());
			dto.setListOrder(entity.getListOrder());
			dto.setVersion(entity.getVersion());
			dto.setRequestType(requestTypeMapper.toDto(entity.getRequestType()));
			dto.setAttachmentType(attachmentTypeMapper.toDto(entity.getAttachmentType()));
		}
		
		return dto;
	}

	@Override
	protected RequestTypeAttachmentType toEntity(RequestTypeAttachmentTypeDto dto, boolean nullId) {
		
		RequestTypeAttachmentType entity = null;
		
		if(isConvertable(dto)) {
			entity = new RequestTypeAttachmentType();
			entity.setId(nullId ? null : dto.getId());
			entity.setIsActive(dto.getIsActive());
			entity.setListOrder(dto.getListOrder());
			entity.setVersion(dto.getVersion());
			entity.setRequestType(requestTypeMapper.toEntity(dto.getRequestType()));
			entity.setAttachmentType(attachmentTypeMapper.toEntity(dto.getAttachmentType()));
		}
		
		return entity;
	}

}

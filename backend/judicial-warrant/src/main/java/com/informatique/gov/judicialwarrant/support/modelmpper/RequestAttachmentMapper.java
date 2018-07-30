package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestAttachment;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class RequestAttachmentMapper extends AbstractModelMapper<RequestAttachment,RequestAttachmentDto,Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8561216366931451343L;
	
	private ModelMapper<AttachmentType, AttachmentTypeDto, Long> attachmentMappeer;
	private ModelMapper<Request,RequestDto, Long> requestForInternalMapper;

	@Override
	public RequestAttachmentDto toDto(RequestAttachment entity) {
		RequestAttachmentDto dto=null;
		if(isConvertable(entity)){
            dto = new RequestAttachmentDto();
            dto.setId(entity.getId());
            dto.setAttachmentType(attachmentMappeer.toDto(entity.getAttachmentType()));
            dto.setRequest(requestForInternalMapper.toDto(entity.getRequest()));
            dto.setUcmDocumentId(entity.getUcmDocumentId());
            dto.setFileName(entity.getFileName());
          
        }

		return dto;
	}

	@Override
	protected RequestAttachment toEntity(RequestAttachmentDto dto, boolean nullId) {
		RequestAttachment entity=null;
		 if(isConvertable(dto)){
			 entity = new RequestAttachment();
			 entity.setId(dto.getId());
			 entity.setAttachmentType(attachmentMappeer.toEntity(dto.getAttachmentType()));
			 entity.setRequest(requestForInternalMapper.toEntity(dto.getRequest()));
			 entity.setUcmDocumentId(dto.getUcmDocumentId());
			 entity.setFileName(dto.getFileName());
	        }
		return entity;
	}

	
}

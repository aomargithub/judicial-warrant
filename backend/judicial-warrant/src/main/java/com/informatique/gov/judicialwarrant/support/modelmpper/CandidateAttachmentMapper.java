package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.domain.Candidate;
import com.informatique.gov.judicialwarrant.domain.CandidateAttachment;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateDto;

@Component
public class CandidateAttachmentMapper extends AbstractModelMapper<CandidateAttachment, CandidateAttachmentDto, Long> {

	private ModelMapper<AttachmentType, AttachmentTypeDto, Byte> attachmentTypeMapper;
	private ModelMapper<Candidate, CandidateDto, Long> candidateMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public CandidateAttachmentDto toDto(CandidateAttachment entity) {
		CandidateAttachmentDto dto = null;

		if (isConvertable(entity)) {
			dto = new CandidateAttachmentDto();
			dto.setId(entity.getId());
			dto.setUcmDocumentId(entity.getUcmDocumentId());
			dto.setAttachmentType(attachmentTypeMapper.toDto(entity.getAttachmentType()));
			dto.setCandidate(candidateMapper.toDto(entity.getCandidate()));

		}

		return dto;
	}

	@Override
	protected CandidateAttachment toEntity(CandidateAttachmentDto dto, boolean nullId) {
		CandidateAttachment entity = null;

		if (isConvertable(dto)) {
			entity = new CandidateAttachment();
			entity.setAttachmentType(attachmentTypeMapper.toEntity(dto.getAttachmentType()));
			entity.setCandidate(candidateMapper.toEntity(dto.getCandidate()));
			entity.setUcmDocumentId(dto.getUcmDocumentId());
			entity.setId(dto.getId());
		}

		return entity;
	}

}

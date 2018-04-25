package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CandidateStatus;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateStatusDto;
 
@Component
public class CandidateStatusMapper extends AbstractModelMapper<CandidateStatus, CandidateStatusDto, Byte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -782343511755386476L;

	@Override
	public CandidateStatusDto toDto(CandidateStatus entity) {
		CandidateStatusDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new CandidateStatusDto();
			dto.setArabicName(entity.getArabicName());
			dto.setCode(entity.getCode());
			dto.setEnglishName(entity.getEnglishName());
			dto.setId(entity.getId());
		}
		
		return dto;
	}

	@Override
	protected CandidateStatus toEntity(CandidateStatusDto dto, boolean nullId) {
		CandidateStatus entity = null;
		
		if(isConvertable(dto)) {
			entity = new CandidateStatus();
			entity.setId(nullId? null :dto.getId());
			entity.setArabicName(dto.getArabicName());
			entity.setCode(dto.getCode());
			entity.setEnglishName(dto.getEnglishName());
		}
		
		return entity;
	}

}

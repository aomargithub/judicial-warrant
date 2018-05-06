package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.Candidate;
import com.informatique.gov.judicialwarrant.domain.CandidateHistoryLog;
import com.informatique.gov.judicialwarrant.domain.CandidateStatus;
import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateDto;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateHistoryLogDto;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class CandidateMapper extends AbstractModelMapper<Candidate, CandidateDto, Long> {

	private ModelMapper<CandidateStatus, CandidateStatusDto, Byte> candidateStatusMapper;
	private ModelMapper<Request, RequestDto, Long> requestMapper;
	private ModelMapper<OrganizationUnit, OrganizationUnitDto, Short> organizationUnitMapper;
	private ModelMapper<CandidateHistoryLog, CandidateHistoryLogDto, Long> candidateHistoryLogMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public CandidateDto toDto(Candidate entity) {
		CandidateDto dto = null;
		if (isConvertable(entity)) {
			dto = new CandidateDto();
			dto.setArabicName(entity.getArabicName());
			dto.setCivilId(entity.getCivilId());
			dto.setCurrentStatus(candidateStatusMapper.toDto(entity.getCurrentStatus()));
			dto.setEmailAddress(entity.getEmailAddress());
			dto.setEnglishName(entity.getEnglishName());
			dto.setHistortyLogs(candidateHistoryLogMapper.toDto(entity.getHistortyLogs()));
			dto.setId(entity.getId());
			dto.setMobileNumber1(entity.getMobileNumber1());
			dto.setMobileNumber2(entity.getMobileNumber2());
			dto.setOrganizationUnit(organizationUnitMapper.toDto(entity.getOrganizationUnit()));
			dto.setRequest(requestMapper.toDto(entity.getRequest()));

		}
		return dto;
	}

	@Override
	protected Candidate toEntity(CandidateDto dto, boolean nullId) {
		Candidate entity = null;
		if (isConvertable(dto)) {
			entity = new Candidate();
			entity.setArabicName(dto.getArabicName());
			entity.setCivilId(dto.getCivilId());
			entity.setCurrentStatus(candidateStatusMapper.toEntity(dto.getCurrentStatus()));
			entity.setEmailAddress(dto.getEmailAddress());
			entity.setEnglishName(dto.getEnglishName());
			entity.setHistortyLogs(candidateHistoryLogMapper.toEntity(dto.getHistortyLogs()));
			entity.setId(dto.getId());
			entity.setMobileNumber1(dto.getMobileNumber1());
			entity.setMobileNumber2(dto.getMobileNumber2());
			entity.setOrganizationUnit(organizationUnitMapper.toEntity(dto.getOrganizationUnit()));
			entity.setRequest(requestMapper.toEntity(dto.getRequest()));

		}
		return entity;
	}

}

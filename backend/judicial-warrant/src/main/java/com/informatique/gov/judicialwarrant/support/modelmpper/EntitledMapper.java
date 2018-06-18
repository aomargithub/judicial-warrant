package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledAttachment;
import com.informatique.gov.judicialwarrant.domain.EntitledHistoryLog;
import com.informatique.gov.judicialwarrant.domain.EntitledStatus;
import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledHistoryLogDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class EntitledMapper extends AbstractModelMapper<Entitled, EntitledDto, Long> {

	private ModelMapper<EntitledStatus, EntitledStatusDto, Byte> entitledStatusMapper;
	private ModelMapper<OrganizationUnit, OrganizationUnitDto, Short> organizationUnitMapper;
	private ModelMapper<EntitledHistoryLog, EntitledHistoryLogDto, Long> entitledHistoryLogMapper;
	private ModelMapper<EntitledAttachment, EntitledAttachmentDto, Long> entitledAttachmentMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public EntitledDto toDto(Entitled entity) {
		EntitledDto dto = null;
		if (isConvertable(entity)) {
			dto = new EntitledDto();
			dto.setArabicName(entity.getArabicName());
			dto.setCivilId(entity.getCivilId());
			dto.setCurrentStatus(entitledStatusMapper.toDto(entity.getCurrentStatus()));
			dto.setEmailAddress(entity.getEmailAddress());
			dto.setEnglishName(entity.getEnglishName());
			dto.setHistortyLogs(entitledHistoryLogMapper.toDto(entity.getHistortyLogs()));
			dto.setId(entity.getId());
			dto.setMobileNumber1(entity.getMobileNumber1());
			dto.setMobileNumber2(entity.getMobileNumber2());
			dto.setAttachments(entitledAttachmentMapper.toDto(entity.getAttachments()));
			dto.setOrganizationUnit(organizationUnitMapper.toDto(entity.getOrganizationUnit()));

		}
		return dto;
	}

	@Override
	protected Entitled toEntity(EntitledDto dto, boolean nullId) {
		Entitled entity = null;
		if (isConvertable(dto)) {
			entity = new Entitled();
			entity.setArabicName(dto.getArabicName());
			entity.setCivilId(dto.getCivilId());
			entity.setCurrentStatus(entitledStatusMapper.toEntity(dto.getCurrentStatus()));
			entity.setEmailAddress(dto.getEmailAddress());
			entity.setEnglishName(dto.getEnglishName());
			entity.setHistortyLogs(entitledHistoryLogMapper.toEntity(dto.getHistortyLogs()));
			entity.setId(dto.getId());
			entity.setMobileNumber1(dto.getMobileNumber1());
			entity.setMobileNumber2(dto.getMobileNumber2());
			entity.setAttachments(entitledAttachmentMapper.toEntity(dto.getAttachments()));
			entity.setOrganizationUnit(organizationUnitMapper.toEntity(dto.getOrganizationUnit()));

		}
		return entity;
	}

}

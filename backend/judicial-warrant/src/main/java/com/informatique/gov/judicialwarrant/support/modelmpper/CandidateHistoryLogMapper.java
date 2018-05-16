package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CandidateHistoryLog;
import com.informatique.gov.judicialwarrant.domain.CandidateStatus;
import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateHistoryLogDto;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CandidateHistoryLogMapper extends AbstractModelMapper<CandidateHistoryLog, CandidateHistoryLogDto, Long> {

	private ModelMapper<CandidateStatus, CandidateStatusDto, Byte> candidateStatusMapper;
	private ModelMapper<User, UserDto, Integer> userMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public CandidateHistoryLogDto toDto(CandidateHistoryLog entity) {
		CandidateHistoryLogDto dto = null;
		if (isConvertable(entity)) {
			dto = new CandidateHistoryLogDto();
			dto.setId(entity.getId());
			dto.setNote(entity.getNote());
			dto.setStatus(candidateStatusMapper.toDto(entity.getStatus()));
			dto.setCreateBy(userMapper.toDto(entity.getCreateBy()));
			dto.setCreateDate(entity.getCreateDate());

		}
		return dto;
	}

	@Override
	protected CandidateHistoryLog toEntity(CandidateHistoryLogDto dto, boolean nullId) {
		CandidateHistoryLog entity = null;
		if (isConvertable(dto)) {
			entity = new CandidateHistoryLog();
			entity.setCreateBy(userMapper.toEntity(dto.getCreateBy()));
			entity.setCreateDate(dto.getCreateDate());
			entity.setId(dto.getId());
			entity.setNote(dto.getNote());
			entity.setStatus(candidateStatusMapper.toEntity(dto.getStatus()));
		}
		return entity;
	}

}

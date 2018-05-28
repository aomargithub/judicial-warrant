package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.EntitledHistoryLog;
import com.informatique.gov.judicialwarrant.domain.EntitledStatus;
import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledHistoryLogDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledHistoryLogMapper extends AbstractModelMapper<EntitledHistoryLog, EntitledHistoryLogDto, Long> {

	private ModelMapper<EntitledStatus, EntitledStatusDto, Byte> entitledStatusMapper;
	private ModelMapper<User, UserDto, Integer> userMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public EntitledHistoryLogDto toDto(EntitledHistoryLog entity) {
		EntitledHistoryLogDto dto = null;
		if (isConvertable(entity)) {
			dto = new EntitledHistoryLogDto();
			dto.setId(entity.getId());
			dto.setNote(entity.getNote());
			dto.setStatus(entitledStatusMapper.toDto(entity.getStatus()));
			dto.setCreateBy(userMapper.toDto(entity.getCreateBy()));
			dto.setCreateDate(entity.getCreateDate());

		}
		return dto;
	}

	@Override
	protected EntitledHistoryLog toEntity(EntitledHistoryLogDto dto, boolean nullId) {
		EntitledHistoryLog entity = null;
		if (isConvertable(dto)) {
			entity = new EntitledHistoryLog();
			entity.setCreateBy(userMapper.toEntity(dto.getCreateBy()));
			entity.setCreateDate(dto.getCreateDate());
			entity.setId(dto.getId());
			entity.setNote(dto.getNote());
			entity.setStatus(entitledStatusMapper.toEntity(dto.getStatus()));
		}
		return entity;
	}

}

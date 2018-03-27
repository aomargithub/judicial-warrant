package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.Role;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;

@Component
public class RoleMapper extends AbstractModelMapper<Role, RoleDto, Byte> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RoleDto toDto(Role entity) {
		RoleDto dto = null;
		if (isConvertable(entity)) {
			dto = new RoleDto();
			dto.setArabicName(entity.getArabicName());
			dto.setCode(entity.getCode());
			dto.setEnglishName(entity.getEnglishName());
			dto.setId(entity.getId());
			dto.setIsActive(entity.getIsActive());
			dto.setListOrder(entity.getListOrder());
		}
		return dto;
	}

	@Override
	protected Role toEntity(RoleDto dto, boolean nullId) {
		Role entity = null;
		if (isConvertable(dto)) {
			entity = new Role();
			entity.setArabicName(dto.getArabicName());
			entity.setCode(dto.getCode());
			entity.setEnglishName(dto.getEnglishName());
			entity.setId(dto.getId());
			entity.setIsActive(dto.getIsActive());
			entity.setListOrder(dto.getListOrder());
		}
		return entity;
	}

}

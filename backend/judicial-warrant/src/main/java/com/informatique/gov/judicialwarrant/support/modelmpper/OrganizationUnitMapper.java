package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;

@Component
public class OrganizationUnitMapper extends AbstractModelMapper<OrganizationUnit, OrganizationUnitDto, Short>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8109272240492442701L;

	@Override
	public OrganizationUnitDto toDto(OrganizationUnit entity) {
		OrganizationUnitDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new OrganizationUnitDto();
			dto.setArabicName(entity.getArabicName());
			dto.setEnglishName(entity.getEnglishName());
			dto.setId(entity.getId());
			dto.setIsActive(entity.getIsActive());
			dto.setArabicName(entity.getArabicName());
			dto.setVersion(entity.getVersion());
			dto.setListOrder(entity.getListOrder());
		}
		
		return dto;
	}

	@Override
	protected OrganizationUnit toEntity(OrganizationUnitDto dto, boolean nullId) {
		
		OrganizationUnit entity = null;
		
		if(isConvertable(dto)) {
			entity = new OrganizationUnit();
			entity.setArabicName(dto.getArabicName());
			entity.setEnglishName(dto.getEnglishName());
			entity.setId(dto.getId());
			entity.setIsActive(dto.getIsActive());
			entity.setArabicName(dto.getArabicName());
			entity.setListOrder(dto.getListOrder());
			entity.setVersion(dto.getVersion());
		}
		
		return entity;
	}

}

package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Role;
import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.domain.UserType;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserTypeDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class UserMapper extends AbstractModelMapper<User, UserDto, Integer> {

	private ModelMapper<OrganizationUnit, OrganizationUnitDto, Short> organizationUnitMapper;
	private ModelMapper<Role, RoleDto, Byte> roleMapper;
	private ModelMapper<UserType, UserTypeDto, Integer> userTypeMapper;
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UserDto toDto(User entity) {
		UserDto dto = null;
		if (isConvertable(entity)) {
			dto = new UserDto();
			dto.setArabicName(entity.getArabicName());
			dto.setCivilId(entity.getCivilId());
			dto.setEmailAddress(entity.getEmailAddress());
			dto.setEnglishName(entity.getEnglishName());
			dto.setId(entity.getId());
			dto.setIsActive(entity.getIsActive());
			dto.setLoginName(entity.getLoginName());
			dto.setMobileNumber1(entity.getMobileNumber1());
			dto.setMobileNumber2(entity.getMobileNumber2());
			dto.setOrganizationUnit(organizationUnitMapper.toDto(entity.getOrganizationUnit()));
			dto.setRole(roleMapper.toDto(entity.getRole()));
			dto.setUserType(userTypeMapper.toDto(entity.getUserType()));
		
			dto.setVersion(entity.getVersion());
		}
		return dto;
	}

	@Override
	protected User toEntity(UserDto dto, boolean nullId) {
		User entity = null;
		if (isConvertable(dto)) {
			entity = new User();
			entity.setArabicName(dto.getArabicName());
			entity.setCivilId(dto.getCivilId());
			entity.setEmailAddress(dto.getEmailAddress());
			entity.setEnglishName(dto.getEnglishName());
			entity.setId(dto.getId());
			entity.setIsActive(dto.getIsActive());
			entity.setLoginName(dto.getLoginName());
			entity.setMobileNumber1(dto.getMobileNumber1());
			entity.setMobileNumber2(dto.getMobileNumber2());
			entity.setOrganizationUnit(organizationUnitMapper.toEntity(dto.getOrganizationUnit()));
			entity.setRole(roleMapper.toEntity(dto.getRole()));
			entity.setUserType(userTypeMapper.toEntity(dto.getUserType()));
			
			entity.setVersion(dto.getVersion());
		}
		return entity;
	}

}

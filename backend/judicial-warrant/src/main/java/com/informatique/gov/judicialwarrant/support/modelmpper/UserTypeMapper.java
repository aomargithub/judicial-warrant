package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.UserType;
import com.informatique.gov.judicialwarrant.rest.dto.UserTypeDto;
@Component
public class UserTypeMapper extends AbstractModelMapper<UserType, UserTypeDto, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4131545798600206597L;

	@Override
	public UserTypeDto toDto(UserType entity) {
		// TODO Auto-generated method stub
		UserTypeDto dto=null;
		if(isConvertable(entity)) {
			dto=new UserTypeDto();
			dto.setId(entity.getId());
			dto.setCode(entity.getCode());
		}
		return dto;
	}

	@Override
	protected UserType toEntity(UserTypeDto dto, boolean nullId) {
		// TODO Auto-generated method stub
		UserType userType=null;
		if(isConvertable(dto)) {
			userType=new UserType();
			userType.setId(dto.getId());
			userType.setCode(dto.getCode());
		}
		
		return userType;
	}

}

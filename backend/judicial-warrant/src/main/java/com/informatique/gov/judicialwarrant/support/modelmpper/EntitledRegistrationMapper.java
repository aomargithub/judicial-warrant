package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.domain.EntitledRegistration;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledRegistrationMapper extends AbstractModelMapper<EntitledRegistration, EntitledRegistrationDto, Long>{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelMapper<Request, RequestDto, Long> requestMapper;
	private ModelMapper<CapacityDelegation, CapacityDelegationDto, Long> capacityDelegationMapper;

	@Override	
	public EntitledRegistrationDto toDto(EntitledRegistration entity) {
		
		EntitledRegistrationDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new EntitledRegistrationDto();
			dto.setId(entity.getId());
			dto.setCapacityDelegation(capacityDelegationMapper.toDto(entity.getCapacityDelegation()));
			dto.setRequest(requestMapper.toDto(entity.getRequest()));
		}
		
		return dto;
	}

	@Override
	protected EntitledRegistration toEntity(EntitledRegistrationDto dto, boolean nullId) {
		
		EntitledRegistration entity = null;
		
		if(isConvertable(dto)) {
			entity = new EntitledRegistration();
			entity.setId(nullId ? null : dto.getId());
			entity.setCapacityDelegation(capacityDelegationMapper.toEntity(dto.getCapacityDelegation()));
			entity.setRequest(requestMapper.toEntity(dto.getRequest()));
		}
		
		return entity;
	}

}

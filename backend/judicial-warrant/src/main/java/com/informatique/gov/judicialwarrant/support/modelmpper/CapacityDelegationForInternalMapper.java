package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

import lombok.AllArgsConstructor;

@Component("capacityDelegationForInternalMapper")
@AllArgsConstructor
public class CapacityDelegationForInternalMapper extends AbstractModelMapper<CapacityDelegation, CapacityDelegationDto, Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelMapper<Request, RequestDto, Long> requestForInternalMapper;

	@Override	
	public CapacityDelegationDto toDto(CapacityDelegation entity) {
		CapacityDelegationDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new CapacityDelegationDto();
			dto.setId(entity.getId());
			dto.setJobTitle(entity.getJobTitle());
			dto.setMinisterialDecreeNumber(entity.getMinisterialDecreeNumber());
			dto.setMinisterialDecreeDate(entity.getMinisterialDecreeDate());
			dto.setRequest(requestForInternalMapper.toDto(entity.getRequest()));
		}
		
		return dto;
	}

	@Override
	protected CapacityDelegation toEntity(CapacityDelegationDto dto, boolean nullId) {

		CapacityDelegation entity = null;
		
		if(isConvertable(dto)) {
			entity = new CapacityDelegation();
			entity.setId(nullId ? null : dto.getId());
			entity.setJobTitle(dto.getJobTitle());
			entity.setMinisterialDecreeNumber(dto.getMinisterialDecreeNumber());
			entity.setMinisterialDecreeDate(dto.getMinisterialDecreeDate());
			entity.setRequest(requestForInternalMapper.toEntity(dto.getRequest()));
		}
		
		return entity;
	}
}

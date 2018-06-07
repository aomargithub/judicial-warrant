package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CapacityDelegationMapper extends AbstractModelMapper<CapacityDelegation, CapacityDelegationDto, Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ModelMapper<Request, RequestDto, Long> requestMapper;

	@Override	
	public CapacityDelegationDto toDto(CapacityDelegation entity) {
		CapacityDelegationDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new CapacityDelegationDto();
			dto.setId(entity.getId());
			dto.setJobTitle(entity.getJobTitle());
			dto.setLawNo(entity.getLawNo());
			dto.setRequest(requestMapper.toDto(entity.getRequest()));
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
			entity.setLawNo(dto.getLawNo());
			entity.setRequest(requestMapper.toEntity(dto.getRequest()));
		}
		
		return entity;
	}

}

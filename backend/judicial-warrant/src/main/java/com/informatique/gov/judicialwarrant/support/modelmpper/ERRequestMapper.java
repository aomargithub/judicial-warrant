package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.ERRequest;
import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestResponse;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestResponse;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class ERRequestMapper extends AbstractModelMapper<ERRequest, ERRequestResponse, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6598101623050234667L;
	private ModelMapper<Request, RequestDto, Long> requestMapper;
	private ModelMapper<JwcdRequest, JwcdRequestResponse, Long> jwcdRequestMapper;

	@Override
	public ERRequestResponse toDto(ERRequest entity) {
		ERRequestResponse dto = null;
		
		if(isConvertable(entity)) {
			dto = new ERRequestResponse();
			dto.setId(entity.getId());
			dto.setSerial(entity.getRequest().getSerial());
			RequestDto requestDto = requestMapper.toDto(entity.getRequest());
			dto.setCurrentStatus(requestDto.getCurrentStatus());
			dto.setOrganizationUnit(requestDto.getOrganizationUnit());
			dto.setType(requestDto.getType());
			JwcdRequestResponse jwcdRequestDto = jwcdRequestMapper.toDto(entity.getJwcdRequest());
			dto.setJwcdRequestDto(jwcdRequestDto);
		}
		return dto;
	}

	@Override
	protected ERRequest toEntity(ERRequestResponse dto, boolean nullId) {
		ERRequest entity = null;
		
		if(isConvertable(dto)) {
			entity = new ERRequest();
			entity.setId(nullId ? null : dto.getId());
		}
		return entity;
	}

}

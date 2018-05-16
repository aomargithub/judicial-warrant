package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.ERRequest;
import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalResponse;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class ERRequestForInternalMapper extends AbstractModelMapper<ERRequest, ERRequestForInternalResponse, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6598101623050234667L;
	private ModelMapper<Request, RequestDto, Long> requestMapper;
	private ModelMapper<JwcdRequest, JwcdRequestForInternalResponse, Long> jwcdRequestForInternalMapper;

	@Override
	public ERRequestForInternalResponse toDto(ERRequest entity) {
		ERRequestForInternalResponse dto = null;
		
		if(isConvertable(entity)) {
			dto = new ERRequestForInternalResponse();
			dto.setId(entity.getId());
			dto.setSerial(entity.getRequest().getSerial());
			RequestDto requestDto = requestMapper.toDto(entity.getRequest());
			dto.setCurrentInternalStatus(requestDto.getCurrentInternalStatus());
			dto.setOrganizationUnit(requestDto.getOrganizationUnit());
			dto.setType(requestDto.getType());
			JwcdRequestForInternalResponse jwcdRequestForInternalDto = jwcdRequestForInternalMapper.toDto(entity.getJwcdRequest());
			dto.setJwcdRequestForInternalDto(jwcdRequestForInternalDto);
		}
		return dto;
	}

	@Override
	protected ERRequest toEntity(ERRequestForInternalResponse dto, boolean nullId) {
		ERRequest entity = null;
		
		if(isConvertable(dto)) {
			entity = new ERRequest();
			entity.setId(nullId ? null : dto.getId());
		}
		return entity;
	}

}

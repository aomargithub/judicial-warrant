package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;

@Component
public class RequestMapper extends AbstractModelMapper<Request, RequestDto, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6598101623050234667L;

	@Override
	public RequestDto toDto(Request entity) {
		RequestDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new RequestDto();
			dto.setId(entity.getId());
		}
		return dto;
	}

	@Override
	protected Request toEntity(RequestDto dto, boolean nullId) {
		// TODO Auto-generated method stub
		return null;
	}

}

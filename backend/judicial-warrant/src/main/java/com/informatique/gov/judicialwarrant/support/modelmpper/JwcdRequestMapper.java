package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestDto;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class JwcdRequestMapper extends AbstractModelMapper<JwcdRequest, JwcdRequestDto, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6598101623050234667L;
	private ModelMapper<Request, RequestDto, Long> requestMapper;

	@Override
	public JwcdRequestDto toDto(JwcdRequest entity) {
		JwcdRequestDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new JwcdRequestDto();
			dto.setId(entity.getId());
			dto.setSerial(entity.getRequest().getSerial());
			dto.setJobTitleName(entity.getJobTitle());
			RequestDto requestDto = requestMapper.toDto(entity.getRequest());
			dto.setCurrentStatus(requestDto.getCurrentStatus());
			dto.setOrganizationUnit(requestDto.getOrganizationUnit());
			dto.setType(requestDto.getType());
		}
		return dto;
	}

	@Override
	protected JwcdRequest toEntity(JwcdRequestDto dto, boolean nullId) {
		JwcdRequest entity = null;
		
		if(isConvertable(dto)) {
			entity = new JwcdRequest();
			entity.setId(nullId ? null : dto.getId());
			entity.setJobTitle(dto.getJobTitleName());
		}
		return entity;
	}

}

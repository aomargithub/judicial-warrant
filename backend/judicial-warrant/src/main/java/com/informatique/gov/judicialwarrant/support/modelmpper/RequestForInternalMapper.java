package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestInternalStatus;
import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;

import lombok.AllArgsConstructor;


@Component("requestForInternalMapper")
@AllArgsConstructor
public class RequestForInternalMapper extends AbstractModelMapper<Request, RequestDto, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ModelMapper<RequestInternalStatus, RequestStatusDto, Byte> requestInternalStatusMapper;
	private ModelMapper<OrganizationUnit, OrganizationUnitDto, Short> organizationUnitMapper;
	private ModelMapper<RequestType, RequestTypeDto, Byte> requestTypeMapper;
	
	@Override
	public RequestDto toDto(Request entity) {
		RequestDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new RequestDto();
			dto.setId(entity.getId());
			dto.setSerial(entity.getSerial());
			dto.setCurrentStatus(requestInternalStatusMapper.toDto(entity.getCurrentInternalStatus()));
			dto.setOrganizationUnit(organizationUnitMapper.toDto(entity.getOrganizationUnit()));
			dto.setType(requestTypeMapper.toDto(entity.getType()));
			dto.setVersion(entity.getVersion());
		}
		return dto;
	}

	@Override
	protected Request toEntity(RequestDto dto, boolean nullId) {
		Request entity = null;
		
		if(isConvertable(dto)) {
			entity = new Request();
			entity.setId(nullId ? null : dto.getId());
			entity.setSerial(dto.getSerial());
			entity.setCurrentInternalStatus(requestInternalStatusMapper.toEntity(dto.getCurrentStatus()));
			entity.setOrganizationUnit(organizationUnitMapper.toEntity(dto.getOrganizationUnit()));
			entity.setType(requestTypeMapper.toEntity(dto.getType()));
			entity.setVersion(dto.getVersion());
		}
		return entity;
	}
}

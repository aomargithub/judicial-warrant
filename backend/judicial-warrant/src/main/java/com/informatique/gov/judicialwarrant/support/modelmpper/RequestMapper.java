package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestInternalStatus;
import com.informatique.gov.judicialwarrant.domain.RequestStatus;
import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestInternalStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class RequestMapper extends AbstractModelMapper<Request, RequestDto, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6598101623050234667L;
	private ModelMapper<RequestStatus, RequestStatusDto, Byte> requestStatusMapper;
	private ModelMapper<RequestInternalStatus, RequestInternalStatusDto, Byte> requestInternalStatusMapper;
	private ModelMapper<OrganizationUnit, OrganizationUnitDto, Short> organizationUnitMapper;
	private ModelMapper<RequestType, RequestTypeDto, Byte> requestTypeMapper;

	@Override
	public RequestDto toDto(Request entity) {
		RequestDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new RequestDto();
			dto.setId(entity.getId());
			dto.setSerial(entity.getSerial());
			dto.setCurrentStatus(requestStatusMapper.toDto(entity.getCurrentStatus()));
			dto.setCurrentInternalStatus(requestInternalStatusMapper.toDto(entity.getCurrentInternalStatus()));
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
			entity.setCurrentStatus(requestStatusMapper.toEntity(dto.getCurrentStatus()));
			entity.setOrganizationUnit(organizationUnitMapper.toEntity(dto.getOrganizationUnit()));
			entity.setType(requestTypeMapper.toEntity(dto.getType()));
			entity.setVersion(dto.getVersion());
		}
		return entity;
	}

}

package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestHistoryLog;
import com.informatique.gov.judicialwarrant.domain.RequestStatus;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestHistoryLogDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;

@Component
public class RequestMapper extends AbstractModelMapper<Request, RequestDto, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6598101623050234667L;
	private ModelMapper<RequestStatus, RequestStatusDto, Byte> requestStatusMapper;
	private ModelMapper<OrganizationUnit, OrganizationUnitDto, Short> organizationUnitMapper;
	private ModelMapper<RequestHistoryLog, RequestHistoryLogDto, Long> requestHistoryLogMapper;

	@Override
	public RequestDto toDto(Request entity) {
		RequestDto dto = null;
		
		if(isConvertable(entity)) {
			dto = new RequestDto();
			dto.setId(entity.getId());
			dto.setSerial(entity.getSerial());
			dto.setCurrentStatus(requestStatusMapper.toDto(entity.getCurrentStatus()));
			dto.setHistortyLogs(requestHistoryLogMapper.toDto(entity.getHistortyLogs()));
			dto.setOrganizationUnit(organizationUnitMapper.toDto(entity.getOrganizationUnit()));
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
			entity.setHistortyLogs(requestHistoryLogMapper.toEntity(dto.getHistortyLogs()));
			entity.setOrganizationUnit(organizationUnitMapper.toEntity(dto.getOrganizationUnit()));
		}
		return entity;
	}

}

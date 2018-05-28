package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.OrganizationUnitRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestInternalStatusRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestStatusRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.service.InternalRequestHistoryLogService;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.service.RequestSerialService;
import com.informatique.gov.judicialwarrant.service.SecurityService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements InternalRequestService {
	
	private RequestRepository requestRepository;
	private RequestTypeRepository requestTypeRepository;
	private RequestStatusRepository requestStatusRepository;
	private RequestInternalStatusRepository requestInternalStatusRepository;
	private RequestSerialService requestSerialService;
	private OrganizationUnitRepository organizationUnitRepository;
	private InternalRequestHistoryLogService requestHistoryLogService;
	private SecurityService securityService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request create(RequestTypeEnum requestTypeEnum)
			throws JudicialWarrantException {
		Request request = null;
		try {
			request = new Request();
			OrganizationUnitDto organizationUnitDto = securityService.getUserDetails()
					.getOrganizationUnit();
			request.setOrganizationUnit(organizationUnitRepository.findById(organizationUnitDto.getId()).get());
			request.setCurrentStatus(requestStatusRepository.findByCode(RequestStatusEnum.DRAFT.getCode()));
			RequestType requestType = requestTypeRepository.findByCode(requestTypeEnum.getCode());
			request.setType(requestType);

			String serial = requestSerialService.getRequestSerial(requestType);
			request.setSerial(serial);
			request = requestRepository.save(request);
			requestHistoryLogService.create(request);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return request;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request update(Request request) throws JudicialWarrantException {
		try {
			notNull(request, "request must be set");
			request = requestRepository.save(request);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return request;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request changeStatus(Request request, RequestInternalStatusEnum requestInternalStatusEnum, String note)
			throws JudicialWarrantException {
		try {
			notNull(request, "request must be set");
			request.setCurrentInternalStatus(
					requestInternalStatusRepository.findByCode(requestInternalStatusEnum.getCode()));
			request.setCurrentStatus(
					requestStatusRepository.findByCode(requestInternalStatusEnum.getRequestStatusEnum().getCode()));
			request = requestRepository.save(request);
			requestHistoryLogService.create(request, note);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return request;
	}

}

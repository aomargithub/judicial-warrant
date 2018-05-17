package com.informatique.gov.judicialwarrant.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestHistoryLog;
import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.OrganizationUnitRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestHistoryLogRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestInternalStatusRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestStatusRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.service.InternalUserService;
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
	private SecurityService securityService;
	private InternalUserService userService;
	private RequestTypeRepository requestTypeRepository;
	private RequestStatusRepository requestStatusRepository;
	private RequestInternalStatusRepository requestInternalStatusRepository;
	private RequestSerialService requestSerialService;
	private RequestHistoryLogRepository requestHistoryLogRepository;
	private OrganizationUnitRepository organizationUnitRepository;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request create(RequestTypeEnum requestTypeEnum, RequestStatusEnum jwcdRequestStatusEnum)
			throws JudicialWarrantException {
		Request request = null;
		try {
			request = new Request();
			OrganizationUnitDto organizationUnitDto = securityService.getUserDetails(securityService.session())
					.getOrganizationUnit();
			request.setOrganizationUnit(organizationUnitRepository.findById(organizationUnitDto.getId()).get());
			request.setCurrentStatus(requestStatusRepository.findByCode(jwcdRequestStatusEnum.getCode()));
			RequestType requestType = requestTypeRepository.findByCode(requestTypeEnum.getCode());
			request.setType(requestType);

			String serial = requestSerialService.getRequestSerial(requestType);
			request.setSerial(serial);
			request = requestRepository.save(request);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return request;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request create(RequestTypeEnum requestTypeEnum, JwcdRequestData jwcdRequestData) throws JudicialWarrantException {
		Request request = null;
		try {
			request = create(requestTypeEnum, RequestStatusEnum.DRAFT);
			RequestHistoryLog requestHistoryLog = new RequestHistoryLog();
			requestHistoryLog.setRequest(request);
			requestHistoryLog.setCreateBy(userService.getByLoginName(securityService.getPrincipal()));
			requestHistoryLog.setCreateDate(new Date());
			requestHistoryLog.setInternalStatus(request.getCurrentInternalStatus());
			requestHistoryLog.setStatus(request.getCurrentStatus());
			requestHistoryLog.setNote(jwcdRequestData.getNotes());
			requestHistoryLogRepository.save(requestHistoryLog);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return request;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request update(Request request) throws JudicialWarrantException {
		try {
			request = requestRepository.save(request);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return request;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request changeStatus(Request request, RequestInternalStatusEnum requestInternalStatusEnum, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		try {
			request.setCurrentInternalStatus(
					requestInternalStatusRepository.findByCode(requestInternalStatusEnum.getCode()));
			request.setCurrentStatus(
					requestStatusRepository.findByCode(requestInternalStatusEnum.getRequestStatusEnum().getCode()));
			request = requestRepository.save(request);
			RequestHistoryLog requestHistoryLog = new RequestHistoryLog();
			requestHistoryLog.setRequest(request);
			requestHistoryLog.setCreateBy(userService.getByLoginName(securityService.getPrincipal()));
			requestHistoryLog.setCreateDate(new Date());
			requestHistoryLog.setInternalStatus(request.getCurrentInternalStatus());
			requestHistoryLog.setStatus(request.getCurrentStatus());
			requestHistoryLog.setNote(jwcdRequestNotesData.getNotes());
			requestHistoryLogRepository.save(requestHistoryLog);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return request;
	}

}

package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.OrganizationUnitRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestInternalStatusRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestStatusRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestTypeRepository;
import com.informatique.gov.judicialwarrant.service.InternalOrganizationUnitService;
import com.informatique.gov.judicialwarrant.service.InternalRequestHistoryLogService;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.service.RequestSerialService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;
import com.informatique.gov.judicialwarrant.support.integration.contentmanger.ContentManager;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements InternalRequestService {
	
	private InternalOrganizationUnitService organizationunitService;
	private InternalRequestHistoryLogService requestHistoryLogService;
	private RequestRepository requestRepository;
	private RequestTypeRepository requestTypeRepository;
	private RequestStatusRepository requestStatusRepository;
	private RequestInternalStatusRepository requestInternalStatusRepository;
	private RequestSerialService requestSerialService;
	private OrganizationUnitRepository organizationUnitRepository;
	private ContentManager contentManager;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request create(RequestTypeEnum requestTypeEnum)
			throws JudicialWarrantException {
		Request request = new Request();
		OrganizationUnit organizationUnit = organizationunitService.getByCurrentUser();
		request.setOrganizationUnit(organizationUnit);
		return create(request, requestTypeEnum);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Request create(Request request, RequestTypeEnum requestTypeEnum)
			throws JudicialWarrantException {
		
		Request newRequest = null;
		try {
			newRequest = new Request();
			newRequest.setCurrentStatus(requestStatusRepository.findByCode(RequestStatusEnum.DRAFT.getCode()));
			RequestType requestType = requestTypeRepository.findByCode(requestTypeEnum.getCode());
			newRequest.setType(requestType);
			String serial = requestSerialService.getRequestSerial(requestType);
			newRequest.setSerial(serial);
			newRequest.setOrganizationUnit(organizationUnitRepository.findById(request.getOrganizationUnit().getId()).get());
			newRequest = requestRepository.save(newRequest);
//			contentManager.createFolder(request.getSerial(), false, null);
			requestHistoryLogService.create(newRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return newRequest;
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

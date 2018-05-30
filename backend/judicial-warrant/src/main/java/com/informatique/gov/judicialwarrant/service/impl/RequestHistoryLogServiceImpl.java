package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestHistoryLog;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestHistoryLogRepository;
import com.informatique.gov.judicialwarrant.service.InternalRequestHistoryLogService;
import com.informatique.gov.judicialwarrant.service.InternalUserService;
import com.informatique.gov.judicialwarrant.service.SecurityService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestHistoryLogServiceImpl implements InternalRequestHistoryLogService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InternalUserService userService;
	private SecurityService securityService;
	private RequestHistoryLogRepository requestHistoryLogRepository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public RequestHistoryLog create(Request request) throws JudicialWarrantException{
		return create(request, null);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public RequestHistoryLog create(Request request, String note) throws JudicialWarrantException{
		RequestHistoryLog requestHistoryLog = null;
		try {
			notNull(request, "request must be set");
			requestHistoryLog = new RequestHistoryLog();
			requestHistoryLog.setRequest(request);
			requestHistoryLog.setCreateBy(userService.getByLoginName(securityService.getPrincipal()));
			requestHistoryLog.setCreateDate(new Date());
			requestHistoryLog.setInternalStatus(request.getCurrentInternalStatus());
			requestHistoryLog.setStatus(request.getCurrentStatus());
			requestHistoryLog.setNote(note);
			requestHistoryLogRepository.save(requestHistoryLog);
		}catch(Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return requestHistoryLog;
	}

}

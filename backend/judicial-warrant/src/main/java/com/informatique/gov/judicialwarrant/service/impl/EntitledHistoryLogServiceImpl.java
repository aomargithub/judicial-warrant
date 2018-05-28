package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledHistoryLog;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledHistoryLogRepository;
import com.informatique.gov.judicialwarrant.service.InternalEntitledHistoryLogService;
import com.informatique.gov.judicialwarrant.service.InternalUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntitledHistoryLogServiceImpl implements InternalEntitledHistoryLogService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InternalUserService userService;
	private EntitledHistoryLogRepository entitledHistoryLogRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledHistoryLog create(Entitled entitled, String note) throws JudicialWarrantException {
		EntitledHistoryLog entity = null;
		try {
			notNull(entitled, "entitled must be set");
			entity = new EntitledHistoryLog();
			entity.setEntitled(entitled);
			entity.setCreateBy(userService.getByCurrentUser());
			entity.setCreateDate(new Date());
			entity.setStatus(entitled.getCurrentStatus());
			entity.setNote(note);
			entity = entitledHistoryLogRepository.save(entity);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return entity;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledHistoryLog create(Entitled entitled) throws JudicialWarrantException {
		return create(entitled, null);
	}

}

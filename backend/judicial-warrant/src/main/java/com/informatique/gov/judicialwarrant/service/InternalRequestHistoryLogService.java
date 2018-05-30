package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestHistoryLog;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface InternalRequestHistoryLogService extends Serializable{

	RequestHistoryLog create(Request request) throws JudicialWarrantException;

	RequestHistoryLog create(Request request, String note) throws JudicialWarrantException;

}

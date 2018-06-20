package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

public interface InternalRequestService extends Serializable {
	
	
	Request create(RequestTypeEnum requestTypeEnum) throws JudicialWarrantException;
	
	Request create(Request request, RequestTypeEnum requestTypeEnum) throws JudicialWarrantException;
	
	Request update(Request request) throws JudicialWarrantException;

	Request changeStatus(Request request, RequestInternalStatusEnum requestInternalStatusEnum, String note) throws JudicialWarrantException;
	
}

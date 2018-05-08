package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;

public interface InternalRequestService extends Serializable {
	
	
	Request create(RequestTypeEnum requestTypeEnum, RequestStatusEnum requestStatusEnum) throws JudicialWarrantException;

	Request create(RequestTypeEnum requestTypeEnum, JwcdRequestData jwcdRequestData) throws JudicialWarrantException;
	
	Request update(Request request) throws JudicialWarrantException;

	Request changeStatus(Request request, RequestInternalStatusEnum requestInternalStatusEnum, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
}

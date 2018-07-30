package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface RequestSerialService extends Serializable {
	
	String getRequestSerial(RequestType requestType) throws JudicialWarrantException;
	
}

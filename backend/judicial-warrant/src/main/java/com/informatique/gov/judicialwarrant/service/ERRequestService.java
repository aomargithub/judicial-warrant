package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestRequest;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestResponse;

public interface ERRequestService extends Serializable {
	List<ERRequestForInternalResponse> getAll() throws JudicialWarrantException;
	
	List<ERRequestResponse> getAllByOrganizationUnit() throws JudicialWarrantException;

	Short getVersionBySerial(String serial) throws JudicialWarrantException;
	
	ERRequestForInternalResponse getBySerial(String serial) throws JudicialWarrantException;
	
	ERRequestResponse getBySerialAndOrganizationUnit(String serial) throws JudicialWarrantException;
	
	ERRequestResponse createRequest(ERRequestRequest erRequestRequest) throws JudicialWarrantException;
	
	ERRequestResponse updateRequest(String serial, ERRequestRequest erRequestRequest) throws JudicialWarrantException;
	
	ERRequestResponse submitRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ERRequestForInternalResponse incompleteRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ERRequestForInternalResponse rejectRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ERRequestForInternalResponse inprogressRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ERRequestForInternalResponse inTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ERRequestForInternalResponse passTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ERRequestForInternalResponse failTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ERRequestForInternalResponse issuedRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
}

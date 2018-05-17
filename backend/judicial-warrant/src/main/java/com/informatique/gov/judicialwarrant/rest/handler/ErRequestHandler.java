package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestRequest;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestResponse;

public interface ErRequestHandler {
	ResponseEntity<List<ERRequestForInternalResponse>> getAll() throws JudicialWarrantException;
	
	ResponseEntity<List<ERRequestResponse>> getAllByOrganizationUnit() throws JudicialWarrantException;

	ResponseEntity<ERRequestForInternalResponse> getBySerial(String serial) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestResponse> getBySerialAndOrganizationUnit(String serial) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestResponse> createRequest(ERRequestRequest erRequestRequest) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestResponse> updateRequest(String serial, ERRequestRequest erRequestRequest, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestResponse> submitRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestForInternalResponse> incompleteRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestForInternalResponse> rejectRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestForInternalResponse> inTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestForInternalResponse> passTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestForInternalResponse> failTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<ERRequestForInternalResponse> inprogressRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
		
	ResponseEntity<ERRequestForInternalResponse> issuedRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException;
	
}

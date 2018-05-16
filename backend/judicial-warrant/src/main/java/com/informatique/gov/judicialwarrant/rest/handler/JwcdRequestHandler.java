package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestResponse;

public interface JwcdRequestHandler {
	ResponseEntity<List<JwcdRequestResponse>> getAll() throws JudicialWarrantException;

	ResponseEntity<JwcdRequestResponse> getBySerial(String serial) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestResponse> createRequest(JwcdRequestData jobNameRequestData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestResponse> updateRequest(String serial, JwcdRequestData jobNameRequestData, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestResponse> submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalResponse> incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalResponse> rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalResponse> inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalResponse> lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalResponse> lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalResponse> lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalResponse> issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
}

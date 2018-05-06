package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalDto;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestDto;

public interface JwcdRequestHandler {
	ResponseEntity<List<JwcdRequestDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<JwcdRequestDto> getBySerial(String serial) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestDto> createRequest(JwcdRequestData jobNameRequestData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestDto> updateRequest(String serial, JwcdRequestData jobNameRequestData, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestDto> submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalDto> incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalDto> rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalDto> inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalDto> lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalDto> lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalDto> lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	ResponseEntity<JwcdRequestForInternalDto> issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
}

package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalDto;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestDto;

public interface JwcdRequestService extends Serializable {
	List<JwcdRequestDto> getAll() throws JudicialWarrantException;

	Short getVersionBySerial(String serial) throws JudicialWarrantException;
	
	JwcdRequestDto getBySerial(String serial) throws JudicialWarrantException;
	
	JwcdRequestDto createRequest(JwcdRequestData jobNameRequestData) throws JudicialWarrantException;
	
	JwcdRequestDto updateRequest(String serial, JwcdRequestData jobNameRequestData) throws JudicialWarrantException;
	
	JwcdRequestDto submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalDto incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalDto rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalDto inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalDto lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalDto lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalDto lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalDto issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
}

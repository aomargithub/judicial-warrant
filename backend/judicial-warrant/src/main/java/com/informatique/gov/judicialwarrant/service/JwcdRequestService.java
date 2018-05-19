package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestResponse;

public interface JwcdRequestService extends Serializable {
	List<JwcdRequestForInternalResponse> getAll() throws JudicialWarrantException;
	
	List<JwcdRequestResponse> getAllByOrganizationUnit() throws JudicialWarrantException;

	Short getVersionBySerial(String serial) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse getBySerial(String serial) throws JudicialWarrantException;
	
	JwcdRequestResponse getBySerialByOrganizationUnit(String serial) throws JudicialWarrantException;
	
	JwcdRequestResponse createRequest(JwcdRequestData jobNameRequestData) throws JudicialWarrantException;
	
	JwcdRequestResponse updateRequest(String serial, JwcdRequestData jobNameRequestData) throws JudicialWarrantException;
	
	JwcdRequestResponse submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
	JwcdRequestForInternalResponse issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException;
	
}

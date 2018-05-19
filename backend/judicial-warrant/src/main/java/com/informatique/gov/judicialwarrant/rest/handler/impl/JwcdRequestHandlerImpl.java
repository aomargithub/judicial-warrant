package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.rest.handler.JwcdRequestHandler;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestResponse;
import com.informatique.gov.judicialwarrant.service.JwcdRequestService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwcdRequestHandlerImpl implements JwcdRequestHandler {
	private JwcdRequestService requestService;

	@Override
	public ResponseEntity<List<JwcdRequestForInternalResponse>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<JwcdRequestForInternalResponse>> response = null;
		try {
			List<JwcdRequestForInternalResponse> dtos = requestService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<List<JwcdRequestResponse>> getAllByOrganizationUnit() throws JudicialWarrantException {
		ResponseEntity<List<JwcdRequestResponse>> response = null;
		try {
			List<JwcdRequestResponse> dtos = requestService.getAllByOrganizationUnit();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> getBySerial(String serial) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {
			JwcdRequestForInternalResponse dto = requestService.getBySerial(serial);
			if (dto == null) {
				throw new ResourceNotFoundException(serial);
			}
			response = ResponseEntity.ok().body(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestResponse> getBySerialAndOrganizationUnit(String serial) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestResponse> response = null;
		try {
			JwcdRequestResponse dto = requestService.getBySerialByOrganizationUnit(serial);
			if (dto == null) {
				throw new ResourceNotFoundException(serial);
			}
			response = ResponseEntity.ok().body(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	
	@Override
	public ResponseEntity<JwcdRequestResponse> createRequest(JwcdRequestData jwcdRequestData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestResponse> response = null;
		try {

			JwcdRequestResponse savedDto = requestService.createRequest(jwcdRequestData);

			response = ResponseEntity.status(HttpStatus.CREATED).body(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestResponse> updateRequest(String serial, JwcdRequestData jobNameRequestData, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestResponse> response = null;
		try {

			notNull(jobNameRequestData.getJobTitle(), "JobTitle must be set");

			if (etag == null) {
				throw new PreConditionRequiredException(serial);
			}

			Short version = requestService.getVersionBySerial(serial);

			if (version == null) {
				throw new ResourceNotFoundException(serial);
			}

			if (!version.equals(etag)) {
				throw new ResourceModifiedException(serial, etag, version);
			}

			JwcdRequestResponse dto = requestService.updateRequest(serial, jobNameRequestData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestResponse> submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestResponse> response = null;
		try {

			JwcdRequestResponse dto = requestService.submitRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {

			JwcdRequestForInternalResponse dto = requestService.incompleteRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {

			JwcdRequestForInternalResponse dto = requestService.rejectRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {

			JwcdRequestForInternalResponse dto = requestService.inprogressRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {

			JwcdRequestForInternalResponse dto = requestService.lawAffairsReviewRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {

			JwcdRequestForInternalResponse dto = requestService.lawAffairsAcceptRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {

			JwcdRequestForInternalResponse dto = requestService.lawAffairsRejectRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalResponse> issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalResponse> response = null;
		try {

			JwcdRequestForInternalResponse dto = requestService.issuedRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

}

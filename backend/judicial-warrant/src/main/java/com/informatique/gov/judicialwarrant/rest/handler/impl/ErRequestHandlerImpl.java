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
import com.informatique.gov.judicialwarrant.rest.handler.ErRequestHandler;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestRequest;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestResponse;
import com.informatique.gov.judicialwarrant.service.ERRequestService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ErRequestHandlerImpl implements ErRequestHandler {
	private ERRequestService requestService;

	@Override
	public ResponseEntity<List<ERRequestForInternalResponse>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<ERRequestForInternalResponse>> response = null;
		try {
			List<ERRequestForInternalResponse> dtos = requestService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<List<ERRequestResponse>> getAllByOrganizationUnit() throws JudicialWarrantException {
		ResponseEntity<List<ERRequestResponse>> response = null;
		try {
			List<ERRequestResponse> dtos = requestService.getAllByOrganizationUnit();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestForInternalResponse> getBySerial(String serial) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {
			ERRequestForInternalResponse dto = requestService.getBySerial(serial);
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
	public ResponseEntity<ERRequestResponse> getBySerialAndOrganizationUnit(String serial) throws JudicialWarrantException {
		ResponseEntity<ERRequestResponse> response = null;
		try {
			ERRequestResponse dto = requestService.getBySerialAndOrganizationUnit(serial);
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
	public ResponseEntity<ERRequestResponse> createRequest(ERRequestRequest erRequestRequest)
			throws JudicialWarrantException {
		ResponseEntity<ERRequestResponse> response = null;
		try {

			ERRequestResponse erRequestResponse = requestService.createRequest(erRequestRequest);

			response = ResponseEntity.status(HttpStatus.CREATED).body(erRequestResponse);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestResponse> updateRequest(String serial, ERRequestRequest erRequestRequest, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<ERRequestResponse> response = null;
		try {

			notNull(erRequestRequest, "ErRequest data must be set");

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

			ERRequestResponse dto = requestService.updateRequest(serial, erRequestRequest);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestResponse> submitRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestResponse> response = null;
		try {

			ERRequestResponse dto = requestService.submitRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestForInternalResponse> incompleteRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.incompleteRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> rejectRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.rejectRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestForInternalResponse> inprogressRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.inprogressRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> inTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.inTrainingRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> passTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.passTrainingRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> failTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.failTrainingRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestForInternalResponse> issuedRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.issuedRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

}

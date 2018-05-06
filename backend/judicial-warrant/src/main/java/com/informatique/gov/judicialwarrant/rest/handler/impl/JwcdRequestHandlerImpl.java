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
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalDto;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestDto;
import com.informatique.gov.judicialwarrant.service.JwcdRequestService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwcdRequestHandlerImpl implements JwcdRequestHandler {
	private JwcdRequestService requestService;

	@Override
	public ResponseEntity<List<JwcdRequestDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<JwcdRequestDto>> response = null;
		try {
			List<JwcdRequestDto> dtos = requestService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestDto> getBySerial(String serial) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestDto> response = null;
		try {
			JwcdRequestDto dto = requestService.getBySerial(serial);
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
	public ResponseEntity<JwcdRequestDto> createRequest(JwcdRequestData jwcdRequestData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestDto> response = null;
		try {

			JwcdRequestDto savedDto = requestService.createRequest(jwcdRequestData);

			response = ResponseEntity.status(HttpStatus.CREATED).body(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestDto> updateRequest(String serial, JwcdRequestData jobNameRequestData, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestDto> response = null;
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

			JwcdRequestDto dto = requestService.updateRequest(serial, jobNameRequestData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestDto> submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestDto> response = null;
		try {

			JwcdRequestDto dto = requestService.submitRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalDto> incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalDto> response = null;
		try {

			JwcdRequestForInternalDto dto = requestService.incompleteRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<JwcdRequestForInternalDto> rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalDto> response = null;
		try {

			JwcdRequestForInternalDto dto = requestService.rejectRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalDto> inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalDto> response = null;
		try {

			JwcdRequestForInternalDto dto = requestService.inprogressRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalDto> lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalDto> response = null;
		try {

			JwcdRequestForInternalDto dto = requestService.lawAffairsReviewRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalDto> lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalDto> response = null;
		try {

			JwcdRequestForInternalDto dto = requestService.lawAffairsAcceptRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalDto> lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalDto> response = null;
		try {

			JwcdRequestForInternalDto dto = requestService.lawAffairsRejectRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<JwcdRequestForInternalDto> issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<JwcdRequestForInternalDto> response = null;
		try {

			JwcdRequestForInternalDto dto = requestService.issuedRequest(serial, jwcdRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

}

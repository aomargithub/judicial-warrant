package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledRegistrationHandler;
import com.informatique.gov.judicialwarrant.service.EntitledRegistrationService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledRegistrationHandlerImpl implements EntitledRegistrationHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntitledRegistrationService entitledRegistrationService;

	@Override
	public ResponseEntity<?> generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response,
			String serial) throws JudicialWarrantException {
		ResponseEntity<?> responseEntity = null;
		try {	
			entitledRegistrationService.generateEntitledRegistrationReportByRequestSerial(response, serial);
			responseEntity = ResponseEntity.ok(response);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<List<EntitledRegistrationDto>> getAll(Authentication authentication) throws JudicialWarrantException {
		
		ResponseEntity<List<EntitledRegistrationDto>> response = null;
		try {
			List<EntitledRegistrationDto> dtos = entitledRegistrationService.getAll(authentication);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}


	@Override
	public ResponseEntity<EntitledRegistrationDto> getBySerial(Authentication authentication, String serial) throws JudicialWarrantException {
		ResponseEntity<EntitledRegistrationDto> response = null;
		try {
			EntitledRegistrationDto dto = entitledRegistrationService.getBySerial(authentication, serial);
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
	public ResponseEntity<EntitledRegistrationDto> create(EntitledRegistrationDto entitledRegistrationDto)
			throws JudicialWarrantException {
		ResponseEntity<EntitledRegistrationDto> response = null;
		try {

			EntitledRegistrationDto savedDto = entitledRegistrationService.create(entitledRegistrationDto);

			response = ResponseEntity.status(HttpStatus.CREATED).body(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<EntitledRegistrationDto> update(String serial, EntitledRegistrationDto entitledRegistrationDto, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<EntitledRegistrationDto> response = null;
		try {

			notNull(entitledRegistrationDto, "entitledRegistrationDto must be set");

			if (etag == null) {
				throw new PreConditionRequiredException(serial);
			}

			Short version = entitledRegistrationService.getVersionBySerial(serial);

			if (version == null) {
				throw new ResourceNotFoundException(serial);
			}

			if (!version.equals(etag)) {
				throw new ResourceModifiedException(serial, etag, version);
			}

			EntitledRegistrationDto savedDto = entitledRegistrationService.update(serial, entitledRegistrationDto);

			response = ResponseEntity.ok(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	/*@Override
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
	}*/

}

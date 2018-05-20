package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.handler.CapacityDelegationHandler;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.service.CapacityDelegationService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CapacityDelegationHandlerImpl implements CapacityDelegationHandler {
	private CapacityDelegationService capacityDelegationService;

	@Override
	public ResponseEntity<List<CapacityDelegationDto>> getAll(Authentication authentication) throws JudicialWarrantException {
		ResponseEntity<List<CapacityDelegationDto>> response = null;
		try {
			List<CapacityDelegationDto> dtos = capacityDelegationService.getAll(authentication);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	

	@Override
	public ResponseEntity<CapacityDelegationDto> getBySerial(Authentication authentication, String serial) throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {
			CapacityDelegationDto dto = capacityDelegationService.getBySerial(authentication, serial);
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
	public ResponseEntity<CapacityDelegationDto> create(CapacityDelegationDto capacityDelegationDto)
			throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.create(capacityDelegationDto);

			response = ResponseEntity.status(HttpStatus.CREATED).body(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> update(String serial, CapacityDelegationDto capacityDelegationDto, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			notNull(capacityDelegationDto.getJobTitle(), "jobTitle must be set");

			if (etag == null) {
				throw new PreConditionRequiredException(serial);
			}

			Short version = capacityDelegationService.getVersionBySerial(serial);

			if (version == null) {
				throw new ResourceNotFoundException(serial);
			}

			if (!version.equals(etag)) {
				throw new ResourceModifiedException(serial, etag, version);
			}

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.update(serial, capacityDelegationDto);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> submit(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.submit(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> incomplete(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.incomplete(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<CapacityDelegationDto> reject(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.reject(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> accept(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.accept(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> lawAffairsReview(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest)
			throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.lawAffairsReview(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> lawAffairsAccept(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest)
			throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.lawAffairsAccept(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> lawAffairsReject(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest)
			throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.lawAffairsReject(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> issue(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto savedCapacityDelegationDto = capacityDelegationService.issue(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(savedCapacityDelegationDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

}

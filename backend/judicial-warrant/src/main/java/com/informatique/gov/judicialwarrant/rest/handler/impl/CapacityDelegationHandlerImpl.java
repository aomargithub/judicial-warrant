package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotModifiedException;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.CapacityDelegationHandler;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.service.CapacityDelegationService;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CapacityDelegationHandlerImpl implements CapacityDelegationHandler {

	private CapacityDelegationService capacityDelegationService;
	private RequestAttachmentService requestAttachmentService;

	@Override
	public ResponseEntity<List<CapacityDelegationDto>> getAll(Authentication authentication)
			throws JudicialWarrantException {
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
	public ResponseEntity<CapacityDelegationDto> getBySerial(Authentication authentication, String serial)
			throws JudicialWarrantException {
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
	public ResponseEntity<CapacityDelegationDto> update(String serial, CapacityDelegationDto capacityDelegationDto,
			Short etag) throws JudicialWarrantException {
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

			CapacityDelegationDto dto = capacityDelegationService.update(serial, capacityDelegationDto);

			response = ResponseEntity.ok().eTag(dto.getVersion().toString()).body(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<CapacityDelegationDto> submit(String serial,
			CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest)
			throws JudicialWarrantException {
		ResponseEntity<CapacityDelegationDto> response = null;
		try {

			CapacityDelegationDto dto = capacityDelegationService.submit(serial, capacityDelegationChangeStatusRequest);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<List<RequestAttachmentDto>> getAllRequestAttachmentByRequestSerial(String serial)
			throws JudicialWarrantException {
		ResponseEntity<List<RequestAttachmentDto>> response = null;
		try {
			List<RequestAttachmentDto> dtos = requestAttachmentService.getAllByRequestSerial(serial);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestAttachmentDto> getRequestAttachmentById(String serial, Long id, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			notNull(id, "id must be set");
			RequestAttachmentDto dto = null;

			if (etag != null) {
				Short version = requestAttachmentService.getVersionById(serial, id);

				if (version == null) {
					throw new ResourceNotFoundException(id);
				}

				if (version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}

			dto = requestAttachmentService.getById(serial, id);

			if (dto == null) {
				throw new ResourceNotFoundException(id);
			}

			response = ResponseEntity.ok().eTag(dto.getVersion().toString()).body(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return response;
	}

	@Override
	public ResponseEntity<RequestAttachmentDto> updateRequestAttachment(String serial, RequestAttachmentDto dto,
			Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			notNull(dto, "candidateAttachmentDto must be set");
			notNull(id, "id must be set");

			if (etag == null) {
				throw new PreConditionRequiredException(id);
			}

			Short version = requestAttachmentService.getVersionById(serial, id);

			if (version == null) {
				throw new ResourceNotFoundException(id);
			}

			if (!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}

			dto.setId(id);
			dto.setVersion(etag);

			RequestAttachmentDto savedDto = requestAttachmentService.update(serial, dto, id);

			response = ResponseEntity.ok().body(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestAttachmentDto> createRequestAttachment(String serial, RequestAttachmentDto dto,
			MultipartFile file) throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {

			RequestAttachmentDto savedDto = requestAttachmentService.create(serial, dto, file);

			response = ResponseEntity.ok(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<?> deleteRequestAttachment(String serial, Long id) throws JudicialWarrantException {
		ResponseEntity<?> response = null;
		try {

			requestAttachmentService.delete(serial, id);

			response = ResponseEntity.ok().build();

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<byte[]> downloadFile(String serial, Long id, String ucmDocumentId)
			throws JudicialWarrantException {
		ResponseEntity<byte[]> responseEntity = null;
		try {

			byte[] bytes = requestAttachmentService.downloadFile(serial, id, ucmDocumentId);

			responseEntity = ResponseEntity.ok(bytes);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return responseEntity;
	}

}

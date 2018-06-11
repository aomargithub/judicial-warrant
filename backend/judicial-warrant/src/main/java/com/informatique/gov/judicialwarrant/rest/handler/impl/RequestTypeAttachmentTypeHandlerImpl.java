package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotModifiedException;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestTypeAttachmentTypeHandler;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RequestTypeAttachmentTypeHandlerImpl implements RequestTypeAttachmentTypeHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6688098430686290417L;

	private RequestTypeAttachmentTypeService requestTypeAttachmentTypeService;
	
	@Override
	public ResponseEntity<List<RequestTypeAttachmentTypeDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<RequestTypeAttachmentTypeDto>> response = null;
		try {
			List<RequestTypeAttachmentTypeDto> dtos = requestTypeAttachmentTypeService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestTypeAttachmentTypeDto> getById(Short id, Short etag) throws JudicialWarrantException {
		ResponseEntity<RequestTypeAttachmentTypeDto> response = null;
		try {
			notNull(id, "id must be set");
			RequestTypeAttachmentTypeDto dto = null;

			if (etag != null) {
				Short version = requestTypeAttachmentTypeService.getVersionById(id);

				if (version == null) {
					throw new ResourceNotFoundException(id);
				}

				if (version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}

			dto = requestTypeAttachmentTypeService.getById(id);

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
	public ResponseEntity<List<RequestTypeAttachmentTypeDto>> getByRequestTypeId(Byte id)
			throws JudicialWarrantException {
		ResponseEntity<List<RequestTypeAttachmentTypeDto>> response = null;
		try {
			notNull(id, "id must be set");
			List<RequestTypeAttachmentTypeDto> dtos = null;

			dtos = requestTypeAttachmentTypeService.getByRequestTypeId(id);
			response = ResponseEntity.ok(dtos);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return response;
	}

	@Override
	public ResponseEntity<RequestTypeAttachmentTypeDto> save(RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto)
			throws JudicialWarrantException {
		ResponseEntity<RequestTypeAttachmentTypeDto> response = null;
		try {
			requestTypeAttachmentTypeDto = requestTypeAttachmentTypeService.save(requestTypeAttachmentTypeDto);
			response = ResponseEntity.ok(requestTypeAttachmentTypeDto);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestTypeAttachmentTypeDto> update(
			RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto, Short id, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<RequestTypeAttachmentTypeDto> response = null;
		try {
			notNull(requestTypeAttachmentTypeDto, "dto must be set");
			notNull(id, "id must be set");

			if (etag == null) {
				throw new PreConditionRequiredException(id);
			}

			Short version = requestTypeAttachmentTypeService.getVersionById(id);

			if (version == null) {
				throw new ResourceNotFoundException(id);
			}

			if (!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}

			requestTypeAttachmentTypeDto.setId(id);
			requestTypeAttachmentTypeDto.setVersion(etag);

			RequestTypeAttachmentTypeDto savedDto = requestTypeAttachmentTypeService
					.update(requestTypeAttachmentTypeDto);

			response = ResponseEntity.ok().body(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestTypeAttachmentTypeDto> delete(Short id) throws JudicialWarrantException {
		ResponseEntity<RequestTypeAttachmentTypeDto> response = null;
		try {
			notNull(id, "id must be set");
			
			requestTypeAttachmentTypeService.delete(id);

			response = ResponseEntity.ok().build();

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	

}

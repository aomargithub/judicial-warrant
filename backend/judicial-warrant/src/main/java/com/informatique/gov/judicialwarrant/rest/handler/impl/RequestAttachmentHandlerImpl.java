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
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.RequestAttachmentHandler;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class RequestAttachmentHandlerImpl implements RequestAttachmentHandler {

	private RequestAttachmentService requestAttachmentService;
	@Override
	public ResponseEntity<List<RequestAttachmentDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<RequestAttachmentDto>> response = null;
		try {
			List<RequestAttachmentDto> dtos = requestAttachmentService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<List<RequestAttachmentDto>> getAllByRequestSerial(String serial)
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
	public ResponseEntity<RequestAttachmentDto> getById(Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			notNull(id, "id must be set");
			RequestAttachmentDto dto = null;
			
			if(etag != null) {
				Short version = requestAttachmentService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = requestAttachmentService.getById(id);
			
			if(dto == null) {
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
	public ResponseEntity<RequestAttachmentDto> update(RequestAttachmentDto dto, Long id, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			notNull(dto, "candidateAttachmentDto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = requestAttachmentService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			dto.setId(id);
			dto.setVersion(etag);
			
			RequestAttachmentDto savedDto = requestAttachmentService.update(dto,id);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestAttachmentDto> create(RequestAttachmentDto dto) throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			
			RequestAttachmentDto savedDto = requestAttachmentService.create(dto);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;	
	}

	@Override
	public ResponseEntity<Void> delete(Long id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			requestAttachmentService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

}

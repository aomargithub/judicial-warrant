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
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.rest.handler.AttachmentTypeHandler;
import com.informatique.gov.judicialwarrant.service.AttachmentTypeService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class AttachmentTypeHandlerImpl implements AttachmentTypeHandler {
	private AttachmentTypeService attachmentTypeService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ResponseEntity<List<AttachmentTypeDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<AttachmentTypeDto>> response = null;
		try {
			List<AttachmentTypeDto> dtos = attachmentTypeService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}

	@Override
	public ResponseEntity<AttachmentTypeDto> getById(Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<AttachmentTypeDto> response = null;
		try {
			notNull(id, "id must be set");
			AttachmentTypeDto dto = null;
			
			if(etag != null) {
				Short version = attachmentTypeService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = attachmentTypeService.getById(id);
			
			if(dto == null) {
				throw new ResourceNotFoundException(id);
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
	public ResponseEntity<AttachmentTypeDto> update(AttachmentTypeDto attachmentTypeDto, Long id,
			Short etag) throws JudicialWarrantException {
		ResponseEntity<AttachmentTypeDto> response = null;
		try {
			notNull(attachmentTypeDto, "candidateAttachmentDto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = attachmentTypeService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			attachmentTypeDto.setId(id);
			attachmentTypeDto.setVersion(etag);
			
			AttachmentTypeDto savedDto = attachmentTypeService.update(attachmentTypeDto);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<AttachmentTypeDto> save(AttachmentTypeDto attachmentTypeDto)
			throws JudicialWarrantException {
		ResponseEntity<AttachmentTypeDto> response = null;
		try {
			
			AttachmentTypeDto savedDto = attachmentTypeService.save(attachmentTypeDto);
			
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
			
			attachmentTypeService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

}

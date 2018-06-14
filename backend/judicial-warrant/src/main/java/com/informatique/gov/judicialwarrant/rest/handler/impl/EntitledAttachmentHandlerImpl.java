package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotModifiedException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledAttachmentHandler;
import com.informatique.gov.judicialwarrant.service.EntitledAttachmentService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class EntitledAttachmentHandlerImpl implements EntitledAttachmentHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntitledAttachmentService entitledAttachmentService;
	
	
	@Override
	public ResponseEntity<EntitledAttachmentDto> getById(Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledAttachmentDto> response = null;
		try {
			notNull(id, "id must be set");
			EntitledAttachmentDto dto = null;
			
			if(etag != null) {
				Short version = entitledAttachmentService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = entitledAttachmentService.getById(id);
			
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
	public ResponseEntity<EntitledAttachmentDto> save(EntitledAttachmentDto dto, MultipartFile file)
			throws JudicialWarrantException {
		ResponseEntity<EntitledAttachmentDto> response = null;
		try {
			
			EntitledAttachmentDto savedDto = entitledAttachmentService.save(dto, file);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;		
	}
	
	@Override
	public ResponseEntity<EntitledAttachmentDto> update(EntitledAttachmentDto dto, Long id,
			Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledAttachmentDto> response = null;
		try {
			notNull(dto, "dto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = entitledAttachmentService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			dto.setId(id);
			dto.setVersion(etag);
			
			EntitledAttachmentDto savedDto = entitledAttachmentService.update(dto);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<List<EntitledAttachmentDto>> getAllByEntitledId(Long id)
			throws JudicialWarrantException {
		ResponseEntity<List<EntitledAttachmentDto>> response = null;
		try {
			List<EntitledAttachmentDto> dtos = entitledAttachmentService.getByEntitledId(id);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}
	
	@Override
	public ResponseEntity<Void> delete(Long id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			entitledAttachmentService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

}

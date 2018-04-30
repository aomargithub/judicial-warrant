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
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.CandidateAttachmentHandler;
import com.informatique.gov.judicialwarrant.service.CandidateAttachmentService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class CandidateAttachmentHandlerImple implements CandidateAttachmentHandler {
	private CandidateAttachmentService candidateAttachmentService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ResponseEntity<List<CandidateAttachmentDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<CandidateAttachmentDto>> response = null;
		try {
			List<CandidateAttachmentDto> dtos = candidateAttachmentService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}

	@Override
	public ResponseEntity<CandidateAttachmentDto> getById(Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<CandidateAttachmentDto> response = null;
		try {
			notNull(id, "id must be set");
			CandidateAttachmentDto dto = null;
			
			if(etag != null) {
				Short version = candidateAttachmentService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = candidateAttachmentService.getById(id);
			
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
	public ResponseEntity<CandidateAttachmentDto> update(CandidateAttachmentDto candidateAttachmentDto, Long id,
			Short etag) throws JudicialWarrantException {
		ResponseEntity<CandidateAttachmentDto> response = null;
		try {
			notNull(candidateAttachmentDto, "candidateAttachmentDto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = candidateAttachmentService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			candidateAttachmentDto.setId(id);
			
			CandidateAttachmentDto savedDto = candidateAttachmentService.update(candidateAttachmentDto);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<CandidateAttachmentDto> save(CandidateAttachmentDto candidateAttachmentDto)
			throws JudicialWarrantException {
		ResponseEntity<CandidateAttachmentDto> response = null;
		try {
			
			CandidateAttachmentDto savedDto = candidateAttachmentService.save(candidateAttachmentDto);
			
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
			
			candidateAttachmentService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

}

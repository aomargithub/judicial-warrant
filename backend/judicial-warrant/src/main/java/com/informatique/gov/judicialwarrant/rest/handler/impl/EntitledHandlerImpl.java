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
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledHandler;
import com.informatique.gov.judicialwarrant.service.EntitledService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class EntitledHandlerImpl implements EntitledHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntitledService entitledService;
	
	@Override
	public ResponseEntity<EntitledDto> getById(Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledDto> response = null;
		try {
			notNull(id, "id must be set");
			EntitledDto dto = null;
			
			if(etag != null) {
				Short version = entitledService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = entitledService.getById(id);
			
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
	public ResponseEntity<EntitledDto> save(EntitledDto entitledDto)
			throws JudicialWarrantException {
		ResponseEntity<EntitledDto> response = null;
		try {
			
			EntitledDto savedDto = entitledService.save(entitledDto);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;		
	}
	
	@Override
	public ResponseEntity<EntitledDto> update(EntitledDto entitledDto, Long id,
			Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledDto> response = null;
		try {
			notNull(entitledDto, "entitledDto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = entitledService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			entitledDto.setId(id);
			entitledDto.setVersion(etag);
			
			EntitledDto savedDto = entitledService.update(entitledDto);
			
			response = ResponseEntity.ok().body(savedDto);
			
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
			
			entitledService.deleteById(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<List<EntitledDto>> getAllByEntitledRegistrationSerial(String serial)
			throws JudicialWarrantException {
		ResponseEntity<List<EntitledDto>> response = null;
		try {
			List<EntitledDto> dtos = entitledService.getAllByEntitledRegistrationSerial(serial);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}

}

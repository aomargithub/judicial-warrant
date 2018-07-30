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
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.handler.OrganizationUnitHandler;
import com.informatique.gov.judicialwarrant.service.OrganizationUnitService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrganizationUnitHandlerImpl implements OrganizationUnitHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1345485407620428163L;

	private OrganizationUnitService organizationUnitService;

	@Override
	public ResponseEntity<List<OrganizationUnitDto>> getAll() throws JudicialWarrantException {

		ResponseEntity<List<OrganizationUnitDto>> response = null;
		try {
			List<OrganizationUnitDto> dtos = organizationUnitService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<OrganizationUnitDto> getById(Short id, Short etag) throws JudicialWarrantException {
		
		ResponseEntity<OrganizationUnitDto> response = null;
		try {
			notNull(id, "id must be set");
			OrganizationUnitDto dto = null;
			
			if(etag != null) {
				Short version = organizationUnitService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = organizationUnitService.getById(id);
			
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
	public ResponseEntity<OrganizationUnitDto> update(OrganizationUnitDto organizationUnitDto, Short id, Short etag) throws JudicialWarrantException{
		ResponseEntity<OrganizationUnitDto> response = null;
		try {
			notNull(organizationUnitDto, "organizationUnitDto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = organizationUnitService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			organizationUnitDto.setId(id);
			organizationUnitDto.setVersion(etag);
			
			OrganizationUnitDto savedDto = organizationUnitService.update(organizationUnitDto);
			
			response = ResponseEntity.ok().eTag(savedDto.getVersion().toString()).body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}
	
	@Override
	public ResponseEntity<OrganizationUnitDto> save(OrganizationUnitDto organizationUnitDto) throws JudicialWarrantException{
		ResponseEntity<OrganizationUnitDto> response = null;
		try {
			
			OrganizationUnitDto savedDto = organizationUnitService.save(organizationUnitDto);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}
	
	@Override
	public ResponseEntity<Void> delete(final Short id) throws JudicialWarrantException{
		ResponseEntity<Void> response = null;
		try {
			
			organizationUnitService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<List<OrganizationUnitDto>> getByIsInternal(Boolean isInternal)
			throws JudicialWarrantException {
		ResponseEntity<List<OrganizationUnitDto>> response = null;
		try {
			List<OrganizationUnitDto> dtos = organizationUnitService.getByIsInternal(isInternal);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;	}
}

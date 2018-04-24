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
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;
import com.informatique.gov.judicialwarrant.rest.handler.RoleHandler;
import com.informatique.gov.judicialwarrant.service.RoleService;
import com.informatique.gov.judicialwarrant.service.impl.RoleServiceImpl;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RoleHandlerImpl implements RoleHandler{

	private RoleService roleService; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ResponseEntity<List<RoleDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<RoleDto>> response = null;
		try {
			List<RoleDto> dtos = roleService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}

	@Override
	public ResponseEntity<RoleDto> getById(Byte id, Short etag) throws JudicialWarrantException {
		ResponseEntity<RoleDto> response = null;
		try {
			notNull(id, "id must be set");
			RoleDto dto = null;
			
			dto = roleService.getById(id);
			
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
	public ResponseEntity<RoleDto> update(RoleDto dto, Byte id, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<RoleDto> response = null;
		try {
			notNull(dto, "roledto must be set");
			notNull(id, "id must be set");
			
			dto.setId(id);
			
			RoleDto savedDto = roleService.update(dto);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<RoleDto> save(RoleDto dto) throws JudicialWarrantException {
		ResponseEntity<RoleDto> response = null;
		try {
			
			RoleDto savedDto = roleService.save(dto);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<Void> delete(Byte id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			roleService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

}

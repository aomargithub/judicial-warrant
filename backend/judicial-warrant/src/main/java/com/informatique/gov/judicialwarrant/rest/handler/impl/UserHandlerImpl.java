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
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.rest.handler.UserHandler;
import com.informatique.gov.judicialwarrant.rest.request.PasswordChangeRequest;
import com.informatique.gov.judicialwarrant.service.UserService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class UserHandlerImpl implements UserHandler {

	private UserService userService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4728922657024097222L;

	@Override
	public ResponseEntity<List<UserDto>> getAll() throws JudicialWarrantException {
		ResponseEntity<List<UserDto>> response = null;
		try {
			List<UserDto> dtos = userService.getAll();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;	
	}
	
	@Override
	public ResponseEntity<List<UserDto>> getAllInternal() throws JudicialWarrantException {
		ResponseEntity<List<UserDto>> response = null;
		try {
			List<UserDto> dtos = userService.getAllInternal();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;	
	}
	
	@Override
	public ResponseEntity<List<UserDto>> getAllExternal() throws JudicialWarrantException {
		ResponseEntity<List<UserDto>> response = null;
		try {
			List<UserDto> dtos = userService.getAllExternal();
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;	
	}

	@Override
	public ResponseEntity<UserDto> save(UserDto dto) throws JudicialWarrantException {
		ResponseEntity<UserDto> response = null;
		try {
           
			UserDto savedDto = userService.save(dto);
							
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;	
	}
	
	@Override
	public ResponseEntity<List<UserDto>> getByRoleIsInternal(Boolean isInternal) throws JudicialWarrantException {
		ResponseEntity<List<UserDto>> response = null;
		try {
			List<UserDto> dtos = userService.getByRoleIsInternal(isInternal);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;	
	}
	
	@Override
	public ResponseEntity<UserDto> getById(Integer id,Short etag) throws JudicialWarrantException {
		ResponseEntity<UserDto> response = null;
		try {
			notNull(id, "id must be set");
			UserDto dto = null;
			if(etag != null) {
				Short version = userService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			
			dto = userService.getById(id);
			
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
	public ResponseEntity<UserDto> update(UserDto dto,Integer id,Short etag) throws JudicialWarrantException {
		ResponseEntity<UserDto> response = null;
		try {
			
			notNull(id, "id must be set");
			
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = userService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			dto.setId(id);
			dto.setVersion(etag); 
			
			UserDto savedDto = userService.update(dto);
			
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			userService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<Void> changePassword(Integer id, String oldPass, String newPass, PasswordChangeRequest passwordChangeRequest)
			throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			userService.changePassword(id, passwordChangeRequest.getOldPassword(), passwordChangeRequest.getNewPassword());
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<List<UserDto>> getByUserTypeCode(String userTypeCode) throws JudicialWarrantException {
		ResponseEntity<List<UserDto>> response = null;
		try {
			List<UserDto> dtos = userService.getByUserTypeCode(userTypeCode);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;	
	}

//	@Override
//	public ResponseEntity<UserDto> createUserInternal(UserDto dto) throws JudicialWarrantException {
//		ResponseEntity<UserDto> response = null;
//		try {
//           
//			UserDto savedDto = userService.createInternal(dto);
//							
//			response = ResponseEntity.ok(savedDto);
//			
//		} catch (JudicialWarrantException e) {
//			throw e;
//		} catch (Exception e) {
//			throw new JudicialWarrantInternalException(e);
//		}return response;	
//	}

//	@Override
//	public ResponseEntity<UserDto> createUserExternal(UserDto dto) throws JudicialWarrantException {
//		ResponseEntity<UserDto> response = null;
//		try {
//           
//			UserDto savedDto = userService.createExternal(dto);
//							
//			response = ResponseEntity.ok(savedDto);
//			
//		} catch (JudicialWarrantException e) {
//			throw e;
//		} catch (Exception e) {
//			throw new JudicialWarrantInternalException(e);
//		}return response;	
//	}

}

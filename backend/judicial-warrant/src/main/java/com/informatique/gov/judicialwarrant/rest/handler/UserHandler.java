package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;

public interface UserHandler extends Serializable{
	ResponseEntity<List<UserDto>> getAll() throws JudicialWarrantException;
	ResponseEntity<List<UserDto>> getAllInternal() throws JudicialWarrantException;
	ResponseEntity<List<UserDto>> getAllExternal() throws JudicialWarrantException;
	ResponseEntity<List<UserDto>> getByUserTypeCode(String  userTypeCode) throws JudicialWarrantException;

	ResponseEntity<UserDto> save(final UserDto dto) throws JudicialWarrantException;
	/*ResponseEntity<UserDto> createUserInternal(final UserDto dto) throws JudicialWarrantException;
	ResponseEntity<UserDto> createUserExternal(final UserDto dto) throws JudicialWarrantException;*/

	ResponseEntity<UserDto> getById(Integer id, Short etag) throws JudicialWarrantException;

	ResponseEntity<UserDto> update(UserDto dto, Integer id, Short etag) throws JudicialWarrantException;

	ResponseEntity<Void>  delete(Integer id) throws JudicialWarrantException;
	
	ResponseEntity<Void>  changePassword(Integer id, String oldPass, String newPass) throws JudicialWarrantException;
	ResponseEntity<List<UserDto>> getByRoleIsInternal(Boolean isInternal) throws JudicialWarrantException;
}

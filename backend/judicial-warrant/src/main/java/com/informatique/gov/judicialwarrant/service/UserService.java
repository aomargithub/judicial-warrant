package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.domain.UserType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;


public interface UserService extends Serializable{
	List<UserDto> getAll() throws JudicialWarrantException;
	List<UserDto> getByUserTypeCode(String userTypeCode) throws JudicialWarrantException;

	UserDto create(final UserDto dto) throws JudicialWarrantException;

	UserDto getById(Integer id) throws JudicialWarrantException;

	UserDto update(UserDto dto) throws JudicialWarrantException;
	
	public Short getVersionById(Integer id) throws JudicialWarrantException;

	public void changePassword(Integer id, String oldPass, String newPass) throws JudicialWarrantException;
	
	void delete(Integer id) throws JudicialWarrantException;
}

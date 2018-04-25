<<<<<<< HEAD
package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;

public interface RoleService extends Serializable{
	List<RoleDto> getAll() throws JudicialWarrantException;

	RoleDto save(final RoleDto dto) throws JudicialWarrantException;

	RoleDto getById(Byte id) throws JudicialWarrantException;

	RoleDto update(RoleDto dto) throws JudicialWarrantException;

	void delete(Byte id) throws JudicialWarrantException;
}
=======
package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;

public interface RoleService extends Serializable{
	List<RoleDto> getAll() throws JudicialWarrantException;

	RoleDto save(final RoleDto dto) throws JudicialWarrantException;

	RoleDto getById(Byte id) throws JudicialWarrantException;

	RoleDto update(RoleDto dto) throws JudicialWarrantException;

	void delete(Byte id) throws JudicialWarrantException;
}
>>>>>>> a52f95e92bc4a1726c4e90258aefbf0f90d036d2

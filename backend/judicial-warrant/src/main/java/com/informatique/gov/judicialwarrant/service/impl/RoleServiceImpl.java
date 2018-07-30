package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Role;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RoleRepository;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;
import com.informatique.gov.judicialwarrant.service.RoleService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleRepository roleRepository;
	private ModelMapper<Role, RoleDto, Byte> roleMapper;

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<RoleDto> getAll() throws JudicialWarrantException {
		List<RoleDto> dtos = null ;
		try {
			List<Role> entities = roleRepository.findAll();
			dtos = roleMapper.toDto(entities);
			
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public RoleDto getById(Byte id) throws JudicialWarrantException {
		RoleDto dto = null;
		try {
			notNull(id, "id must be set");
			
			Role entity = roleRepository.findById(id).get();
			dto = roleMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<RoleDto> getByIsInternal(Boolean isInternal) throws JudicialWarrantException {
		List<RoleDto> dtos = null;
		try {
			notNull(isInternal, "isInternal must be set");
			
			List<Role> entities = roleRepository.findByIsInternal(isInternal);
			dtos = roleMapper.toDto(entities);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}
}

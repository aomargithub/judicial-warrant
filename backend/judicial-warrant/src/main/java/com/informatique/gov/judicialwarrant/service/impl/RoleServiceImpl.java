<<<<<<< HEAD
package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.CandidateAttachment;
import com.informatique.gov.judicialwarrant.domain.Role;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RoleRepository;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;
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
	@Transactional(rollbackFor = Exception.class)
	public RoleDto save(RoleDto dto) throws JudicialWarrantException {
		RoleDto savedDto = null;

		try {
			notNull(dto, "dto must be set");

			Role entiry = roleMapper.toNewEntity(dto);
			
			entiry = roleRepository.save(entiry);
			
			savedDto = roleMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;	
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
	@Transactional(rollbackFor = Exception.class)
	public RoleDto update(RoleDto dto) throws JudicialWarrantException {
		RoleDto savedDto = null;

		try {
			notNull(dto, "dto must be set");			

			Role entiry = roleMapper.toEntity(dto);
			
			entiry = roleRepository.save(entiry);
			
			savedDto = roleMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Byte id) throws JudicialWarrantException {
		try {
			notNull(id, "id must be set");
			roleRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
	}

}
=======
package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.CandidateAttachment;
import com.informatique.gov.judicialwarrant.domain.CreateLog;
import com.informatique.gov.judicialwarrant.domain.Role;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RoleRepository;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;
import com.informatique.gov.judicialwarrant.service.RoleService;
import com.informatique.gov.judicialwarrant.service.SecurityService;
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
	@Autowired
	private SecurityService securityService; 

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<RoleDto> getAll() throws JudicialWarrantException {
		List<RoleDto> dtos = null ;
		try {
			List<Role> entities = roleRepository.findAll();
			dtos = roleMapper.toDto(entities);
			System.out.println(entities);
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public RoleDto save(RoleDto dto) throws JudicialWarrantException {
		RoleDto savedDto = null;

		try {
			notNull(dto, "dto must be set");

			Role entiry = roleMapper.toNewEntity(dto);
			entiry.setCreateLog(new CreateLog(securityService.getPrincipal(), new Date()));
			entiry = roleRepository.save(entiry);
			
			savedDto = roleMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;	
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
	@Transactional(rollbackFor = Exception.class)
	public RoleDto update(RoleDto dto) throws JudicialWarrantException {
		RoleDto savedDto = null;

		try {
			notNull(dto, "dto must be set");			

			Role entiry = roleMapper.toEntity(dto);
			
			entiry = roleRepository.save(entiry);
			
			savedDto = roleMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Byte id) throws JudicialWarrantException {
		try {
			notNull(id, "id must be set");
			roleRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
	}

}
>>>>>>> a52f95e92bc4a1726c4e90258aefbf0f90d036d2

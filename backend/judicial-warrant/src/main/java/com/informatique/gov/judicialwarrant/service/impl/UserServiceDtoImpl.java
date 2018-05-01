package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.UserRepository;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.service.UserService;
import com.informatique.gov.judicialwarrant.support.modelmpper.UserMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceDtoImpl implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 249744152632344882L;
	private UserRepository userRepository;
	private UserMapper userMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<UserDto> getAll() throws JudicialWarrantException {
		List<UserDto> dtos = null ;
		try {
			List<User> entities = userRepository.findAll();
			dtos = userMapper.toDto(entities);
			
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto save(UserDto dto) throws JudicialWarrantException {
		UserDto savedDto = null;

		try {
			notNull(dto, "dto must be set");

			User entiry = userMapper.toNewEntity(dto);	
			entiry.getOrganizationUnit().setVersion((short) 1);
			
			entiry = userRepository.save(entiry);
			
			savedDto = userMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=true)
	public UserDto getById(Integer id) throws JudicialWarrantException {
		UserDto dto = null;
		try {
			notNull(id, "id must be set");
			
			User entity = userRepository.findById(id).get();
			dto = userMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto update(UserDto dto) throws JudicialWarrantException {
		UserDto savedDto = null;

		try {
			notNull(dto, "dto must be set");			

			User entiry = userMapper.toEntity(dto);
			entiry.getOrganizationUnit().setVersion((short) 1);
			
			entiry = userRepository.save(entiry);
			
			savedDto = userMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Integer id) throws JudicialWarrantException {
		try {
			notNull(id, "id must be set");
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Short getVersionById(Integer id) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(id, "id must be set");
			version = userRepository.findVersionById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;		
	}

}

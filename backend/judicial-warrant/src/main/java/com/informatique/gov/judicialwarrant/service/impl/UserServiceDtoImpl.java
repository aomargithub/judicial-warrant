package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Role;
import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.domain.UserCredentials;
import com.informatique.gov.judicialwarrant.domain.UserType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.OrganizationUnitRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RoleRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.UserCredientialRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.UserRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.UserTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.service.UserPasswordService;
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
	private UserCredientialRepository credientialRepository;
	private UserTypeRepository typeRepository;
	private RoleRepository roleRepository;
	private UserMapper userMapper;
	private OrganizationUnitRepository organizationUnitRepository;
	private UserPasswordService passwordService;
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<UserDto> getAll() throws JudicialWarrantException {
		List<UserDto> dtos = null;
		try {
			List<User> entities = userRepository.findAll();
			dtos = userMapper.toDto(entities);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto saveExternal(UserDto dto) throws JudicialWarrantException {
		UserDto savedDto = null;

		try {
			notNull(dto, "dto must be set");
			String password = passwordService.generateRandomUserPassword();
			// String passwordHashing = HashingPasswordUtil.hash(password);

			String passwordHashing = passwordEncoder.encode(password);
			// String message = "Hi " + dto.getLoginName() + "\n Password : " + password;
			// String subject = "User Password";
			

			User entiry = userMapper.toNewEntity(dto);
		//	entiry.setUserCredentials(credentials);
			Optional<OrganizationUnit> organizationUnit = organizationUnitRepository
					.findById(dto.getOrganizationUnit().getId());
			
			entiry.setOrganizationUnit(organizationUnit.get());
			
			Optional<Role> role=roleRepository.findById(dto.getRole().getId());
			entiry.setRole(role.get());
			Optional<UserType> userType=typeRepository.findById(new Integer(1));
			entiry.setUserType(userType.get());

			entiry = userRepository.save(entiry);
			UserCredentials credentials = new UserCredentials();
			credentials.setId(entiry.getId());
			credentials.setPassword(passwordHashing);
			credientialRepository.save(credentials);

			savedDto = userMapper.toDto(entiry);

			// passwordService.sendUserPasswordToEmail(message, dto.getEmailAddress(),
			// subject);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto saveInternal(UserDto dto) throws JudicialWarrantException {
		UserDto savedDto = null;

		try {
			notNull(dto, "dto must be set");

			User entiry = userMapper.toNewEntity(dto);
			
			Optional<OrganizationUnit> organizationUnit = organizationUnitRepository
					.findById(dto.getOrganizationUnit().getId());
			
			entiry.setOrganizationUnit(organizationUnit.get());
			Optional<Role> role=roleRepository.findById(dto.getRole().getId());
			entiry.setRole(role.get());
			Optional<UserType> userType=typeRepository.findById(new Integer(2));
			entiry.setUserType(userType.get());

			entiry = userRepository.save(entiry);

			savedDto = userMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
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
			Optional<OrganizationUnit> organizationUnit = organizationUnitRepository
					.findById(dto.getOrganizationUnit().getId());
			entiry.getOrganizationUnit().setVersion(organizationUnit.get().getVersion());
			entiry.setOrganizationUnit(organizationUnit.get());
			Optional<Role> role=roleRepository.findById(dto.getRole().getId());
			entiry.setRole(role.get());
			Optional<UserType> userType=typeRepository.findById(new Integer(2));
			entiry.setUserType(userType.get());
			
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

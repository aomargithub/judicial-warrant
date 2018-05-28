package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Optional;

import org.springframework.core.env.Environment;
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
import com.informatique.gov.judicialwarrant.persistence.repository.UserCredentialsRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.UserRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.UserTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.UserDetailsDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.service.InternalUserService;
import com.informatique.gov.judicialwarrant.service.SecurityService;
import com.informatique.gov.judicialwarrant.service.UserService;
import com.informatique.gov.judicialwarrant.support.dataenum.UserTypeEnum;
import com.informatique.gov.judicialwarrant.support.ldap.LdapService;
import com.informatique.gov.judicialwarrant.support.modelmpper.UserMapper;
import com.informatique.gov.judicialwarrant.support.utils.RandomPasswordUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, InternalUserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 249744152632344882L;

	private SecurityService securityService;
	private UserRepository userRepository;
	private UserCredentialsRepository userCredentialsRepository;
	private UserTypeRepository userTypeRepository;
	private RoleRepository roleRepository;
	private UserMapper userMapper;
	private OrganizationUnitRepository organizationUnitRepository;
	private PasswordEncoder passwordEncoder;
	private Environment environment;
	private LdapService ldapService;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public User getByLoginName(String loginName) throws JudicialWarrantException {
		User user = null;
		try {

			user = userRepository.findByLoginNameIgnoreCase(loginName);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return user;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public User getByCurrentUser() throws JudicialWarrantException {
		User user = null;
		try {
			UserDetailsDto userDetailsDto = securityService.getUserDetails();
			user = getByLoginName(userDetailsDto.getUsername());
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return user;
	}

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
	public UserDto createExternal(UserDto dto) throws JudicialWarrantException {
		UserDto savedUserDto = null;

		try {
			notNull(dto, "dto must be set");
			String password = RandomPasswordUtil
					.generate(new Integer(environment.getRequiredProperty("app.defaultpasswordlength")));
			// String passwordHashing = HashingPasswordUtil.hash(password);

			String hashedPassword = passwordEncoder.encode(password);
			// String message = "Hi " + dto.getLoginName() + "\n Password : " + password;
			// String subject = "User Password";

			User user = prepareUser(dto);
			UserType userType = userTypeRepository.findByCode(UserTypeEnum.EXTERNAL.getCode());
			user.setUserType(userType);

			user = userRepository.save(user);
			UserCredentials credentials = new UserCredentials();
			credentials.setUser(user);
			credentials.setPassword(hashedPassword);
			userCredentialsRepository.save(credentials);

			savedUserDto = userMapper.toDto(user);

			// passwordService.sendUserPasswordToEmail(message, dto.getEmailAddress(),
			// subject);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedUserDto;
	}

	/**
	 * @param dto
	 * @return
	 */
	private User prepareUser(UserDto dto) {
		User user = userMapper.toNewEntity(dto);
		Optional<OrganizationUnit> organizationUnit = organizationUnitRepository
				.findById(dto.getOrganizationUnit().getId());

		user.setOrganizationUnit(organizationUnit.get());
		Role role = null;
		if (dto.getRole().getLdapSecurityGroup() != null) {
			role = roleRepository.findByLdapSecurityGroup(dto.getRole().getLdapSecurityGroup());
			ldapService.addMemberToGroup(role.getLdapSecurityGroup(), dto.getLoginName());
		} else {
			role = roleRepository.findByCode(dto.getRole().getCode());

		}
		user.setRole(role);
		return user;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto createInternal(UserDto dto) throws JudicialWarrantException {
		UserDto savedUserDto = null;

		try {
			notNull(dto, "dto must be set");

			User user = prepareUser(dto);
			UserType userType = userTypeRepository.findByCode(UserTypeEnum.INTERNAL.getCode());
			user.setUserType(userType);

			user = userRepository.save(user);

			savedUserDto = userMapper.toDto(user);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedUserDto;
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
		UserDto savedUserDto = null;

		try {
			notNull(dto, "dto must be set");

			User user = userMapper.toEntity(dto);
			Optional<OrganizationUnit> organizationUnit = organizationUnitRepository
					.findById(dto.getOrganizationUnit().getId());
			user.getOrganizationUnit().setVersion(organizationUnit.get().getVersion());
			user.setOrganizationUnit(organizationUnit.get());
			Optional<Role> role = roleRepository.findById(dto.getRole().getId());
			user.setRole(role.get());
			Optional<UserType> userType = userTypeRepository.findById(new Integer(2));
			user.setUserType(userType.get());

			user = userRepository.save(user);

			savedUserDto = userMapper.toDto(user);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedUserDto;
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

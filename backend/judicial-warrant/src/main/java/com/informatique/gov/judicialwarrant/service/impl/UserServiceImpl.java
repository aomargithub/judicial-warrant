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
import com.informatique.gov.judicialwarrant.exception.WrongOldPasswordException;
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
import com.informatique.gov.judicialwarrant.support.utils.MailUtil;
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
	private MailUtil mailUtil;
	


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
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<UserDto> getAllInternal() throws JudicialWarrantException {
		List<UserDto> dtos = null;
		try {
			List<User> entities = userRepository.findByRoleIsInternal(true);
			dtos = userMapper.toDto(entities);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<UserDto> getAllExternal() throws JudicialWarrantException {
		List<UserDto> dtos = null;
		try {
			List<User> entities = userRepository.findByRoleIsInternal(false);
			dtos = userMapper.toDto(entities);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDto save(UserDto dto) throws JudicialWarrantException {

		try {
			Role role = roleRepository.findByCode(dto.getRole().getCode());
			if (role.getIsInternal()) {
				return createInternal(dto, role);
			} else {
				return createExternal(dto, role);
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);

		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<UserDto> getByRoleIsInternal(Boolean isInternal) throws JudicialWarrantException {
		List<UserDto> dtos = null;
		try {
			List<User> entities = userRepository.findByRoleIsInternal(isInternal);
			dtos = userMapper.toDto(entities);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	private UserDto createExternal(UserDto dto, Role role) throws JudicialWarrantException {
		UserDto savedUserDto = null;

		try {
			notNull(dto, "dto must be set");
			
			String password = RandomPasswordUtil.generate(new Integer(environment.getRequiredProperty("app.default-password-length")));
			User user = prepareExternalUser(dto, role, password);
			

			user = userRepository.save(user);
			
			user.getCredentials().setUser(user);
			
			userCredentialsRepository.save(user.getCredentials());

			savedUserDto = userMapper.toDto(user);

			mailUtil.sendAccountCreation(user.getLoginName(), password, user.getEmailAddress(), user.getEnglishName(),
					user.getArabicName());

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedUserDto;
	}

	/**
	 * @param dto
	 * @return
	 */

	private User prepareExternalUser(UserDto dto, Role role, String password) {
		
		Optional<OrganizationUnit> organizationUnit = organizationUnitRepository
				.findById(dto.getOrganizationUnit().getId());
		
		UserType userType = userTypeRepository.findByCode(UserTypeEnum.EXTERNAL.getCode());
		
		
		String hashedPassword = passwordEncoder.encode(password);
		
		UserCredentials credentials = new UserCredentials();
		credentials.setPassword(hashedPassword);
		
		User user = userMapper.toNewEntity(dto);
		user.setUserType(userType);
		user.setOrganizationUnit(organizationUnit.get());

		user.setRole(role);
		user.setCredentials(credentials);
		return user;
	}
	
	private User prepareInternalUser(UserDto dto, Role role) {
		
		OrganizationUnit moj = organizationUnitRepository.findById(Short.parseShort(environment.getRequiredProperty("app.moj-ou-id"))).get();
		UserType userType = userTypeRepository.findByCode(UserTypeEnum.INTERNAL.getCode());
		
		
		User user = userMapper.toNewEntity(dto);
		user.setUserType(userType);
		user.setOrganizationUnit(moj);
		user.setRole(role);		
		return user;
	}

	
	private UserDto createInternal(UserDto dto, Role role) throws JudicialWarrantException {
		UserDto savedUserDto = null;

		try {
			notNull(dto, "dto must be set");


			User user = prepareInternalUser(dto, role);
			

			user = userRepository.save(user);
			ldapService.addMemberToGroup(role.getLdapSecurityGroup(), dto.getLoginName());
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
			user.setOrganizationUnit(organizationUnit.get());
			Optional<Role> role = roleRepository.findById(dto.getRole().getId());
			user.setRole(role.get());
			UserType userType = userTypeRepository.findByCode(dto.getUserType().getCode());
			user.setUserType(userType);

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
			Optional<UserCredentials> credentials = userCredentialsRepository.findById(id);
			if (credentials.isPresent()) {
				userCredentialsRepository.delete(credentials.get());
			}
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changePassword(Integer id, String oldPass, String newPass) throws JudicialWarrantException {
		try {
			UserCredentials userCredentials = userCredentialsRepository.getOne(id);
			if (passwordEncoder.matches(oldPass, userCredentials.getPassword().trim())) {
				userCredentials.setPassword(passwordEncoder.encode(newPass));
				userCredentialsRepository.save(userCredentials);
			} else {
				throw new WrongOldPasswordException();
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}

	@Override
	public List<UserDto> getByUserTypeCode(String userTypeCode) throws JudicialWarrantException {
		List<UserDto> dtos = null;
		try {
			List<User> entities = userRepository.findByUserTypeCode(userTypeCode);
			dtos = userMapper.toDto(entities);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

}

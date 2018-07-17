package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledRegistration;
import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledRegistrationRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestRepository;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.request.EntitledRegistrationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.service.EntitledRegistrationService;
import com.informatique.gov.judicialwarrant.service.EntitledService;
import com.informatique.gov.judicialwarrant.service.InternalCapacityDelegationService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledService;
import com.informatique.gov.judicialwarrant.service.InternalOrganizationUnitService;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.UserRoleEnum;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;
import com.informatique.gov.judicialwarrant.support.report.ReportGeneration;
import com.informatique.gov.judicialwarrant.support.security.JudicialWarrantGrantedAuthority;
import com.informatique.gov.judicialwarrant.support.utils.MailUtil;
import com.informatique.gov.judicialwarrant.support.validator.EntitledRegistrationWorkflowValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntitledRegistrationServiceImpl implements EntitledRegistrationService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InternalRequestService requestService;
	private InternalEntitledService internalEntitledService;
	private EntitledService entitledService;
	private InternalOrganizationUnitService organizationunitService;
	private InternalCapacityDelegationService capacityDelegationService;
	private EntitledRegistrationRepository entitledRegistrationRepository;
	private RequestRepository requestRepository;
	private EntitledRegistrationWorkflowValidator entitledRegistrationWorkflowValidator;
	private ModelMapper<EntitledRegistration, EntitledRegistrationDto, Long> entitledRegistrationMapper;
	private ModelMapper<EntitledRegistration, EntitledRegistrationDto, Long> entitledRegistrationForInternalMapper;
	private MailUtil mailUtil;
	private Environment environment;
	
	List<JudicialWarrantGrantedAuthority> authorities = Arrays.asList(new JudicialWarrantGrantedAuthority(UserRoleEnum.OFFICER), new JudicialWarrantGrantedAuthority(UserRoleEnum.TRAINING_INSTITUTE));

	@Override
	public void generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response, String serial)
			throws JudicialWarrantException {
		try {
			Set<EntitledDto> entitledDtos = entitledService.getAllByEntitledRegistrationSerial(serial);
			if(entitledDtos == null) {
				entitledDtos = new HashSet<>();
				entitledDtos.add(new EntitledDto());
			}
			EntitledRegistrationDto entitledRegistrationDto = getBySerial(SecurityContextHolder.getContext().getAuthentication(), serial);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Request_Serial", entitledRegistrationDto.getRequest().getSerial());
			parameters.put("CapacityDelegation_JobTitle", entitledRegistrationDto.getCapacityDelegation().getJobTitle());
			ReportGeneration.generateReportToResponse("EntitledRegistrationReport", parameters, entitledDtos, response, new Locale("ar"));
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<EntitledRegistrationDto> getAll(Authentication  authentication) throws JudicialWarrantException {
		List<EntitledRegistrationDto> dtos = null;
		try {
			List<EntitledRegistration> entities = null;
			if(authentication.getAuthorities().contains(new JudicialWarrantGrantedAuthority(UserRoleEnum.OFFICER))) {
				entities = entitledRegistrationRepository.findAll();
				dtos = entitledRegistrationForInternalMapper.toDto(entities);
			} else if (authentication.getAuthorities().contains(new JudicialWarrantGrantedAuthority(UserRoleEnum.TRAINING_INSTITUTE))) {
				entities = entitledRegistrationRepository.findByRequestCurrentInternalStatusAndEntitledStatus(RequestInternalStatusEnum.INPROGRESS.getCode(), 
						Arrays.asList(EntitledStatusEnum.ACCEPTED.getCode(), EntitledStatusEnum.TRAINNING.getCode(), 
								EntitledStatusEnum.PASSED.getCode(), EntitledStatusEnum.FAILED.getCode()));
				dtos = entitledRegistrationForInternalMapper.toDto(entities);
			} else {
				OrganizationUnit organizationUnit = organizationunitService.getByCurrentUser();
				entities = entitledRegistrationRepository.findByRequestOrganizationUnitId(organizationUnit.getId());
				dtos = entitledRegistrationMapper.toDto(entities);
			}
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Short getVersionBySerial(String serial) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(serial, "serial must be set");
			version = requestRepository.findVersionBySerial(serial);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public EntitledRegistrationDto getBySerial(Authentication  authentication, String serial) throws JudicialWarrantException {
		
		EntitledRegistrationDto dto = null;
		try {
			notNull(serial, "serial must be set");
			EntitledRegistration entity = null;
			if(authentication.getAuthorities().stream().anyMatch(authorities::contains)) {
				entity = entitledRegistrationRepository.findByRequestSerial(serial);
				dto = entitledRegistrationForInternalMapper.toDto(entity);
			}else {
				OrganizationUnit organizationUnit = organizationunitService.getByCurrentUser();
				entity = entitledRegistrationRepository.findByRequestSerialAndRequestOrganizationUnitId(serial, organizationUnit.getId());
				dto = entitledRegistrationMapper.toDto(entity);
			}			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledRegistrationDto create(EntitledRegistrationDto dto) throws JudicialWarrantException {
		
		EntitledRegistrationDto savedDto = null;
		try {
			
			notNull(dto, "dto must be set");
			EntitledRegistration entity = entitledRegistrationMapper.toNewEntity(dto);
			Request request = requestService.create(RequestTypeEnum.ENTITLED_REGISTRATION);
			
			
			entity.setRequest(request);
			
			
			savedDto = save(entity);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedDto;
	}
	
	private EntitledRegistrationDto save(EntitledRegistration entity) throws JudicialWarrantException {
		
		CapacityDelegation capacityDelegation = capacityDelegationService.getIfValid(entity.getCapacityDelegation().getRequest().getSerial());
		entity.setCapacityDelegation(capacityDelegation);
		entity = entitledRegistrationRepository.save(entity);		
		EntitledRegistrationDto savedDto = entitledRegistrationMapper.toDto(entity);
		
		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledRegistrationDto update(String serial, EntitledRegistrationDto dto)
			throws JudicialWarrantException {
		
		EntitledRegistrationDto savedDto = null;
		try {
			notNull(serial, "serial must be set");
			notNull(dto, "dto must be set");
			
			
			EntitledRegistration entity = getIfValid(serial);
			
			entitledRegistrationWorkflowValidator.validateForUpdate(entity);
			
			savedDto = save(entity);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedDto;
	}
	
	private EntitledRegistration getIfValid(String serial) throws JudicialWarrantException {
		
		EntitledRegistration entity = entitledRegistrationRepository.findByRequestSerial(serial);
		if(entity == null) {
			throw new ResourceNotFoundException(serial);
		}
		return entity;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledRegistrationDto submit(String serial, EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException {
		
		EntitledRegistrationDto savedEntitledRegistrationDto = null;
		try {
			EntitledRegistration entity = getIfValid(serial);
			entitledRegistrationWorkflowValidator.validate(entity, RequestInternalStatusEnum.RECIEVED);
			
			Request request = requestService.changeStatus(entity.getRequest(), RequestInternalStatusEnum.RECIEVED, entitledRegistrationChangeStatusRequest.getNote());
			entity.setRequest(request);
			
			Set<Entitled> entitled = internalEntitledService.changeStatusByEntitledRegistrationId(entity.getId(), EntitledStatusEnum.SUBMITED, null);
			
			
			entity.setEntitled(entitled);
			
			savedEntitledRegistrationDto = entitledRegistrationMapper.toDto(entity);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntitledRegistrationDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledRegistrationDto inComplete(String serial, EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException {
		EntitledRegistrationDto savedEntitledRegistrationDto = null;
		try {
			EntitledRegistration entity = getIfValid(serial);
			entitledRegistrationWorkflowValidator.validate(entity, RequestInternalStatusEnum.INCOMPLETE);
			
			Request request = requestService.changeStatus(entity.getRequest(), RequestInternalStatusEnum.INCOMPLETE, entitledRegistrationChangeStatusRequest.getNote());
			entity.setRequest(request);
			
			Set<Entitled> entitled = internalEntitledService.changeStatusByEntitledRegistrationId(entity.getId(), EntitledStatusEnum.INCOMPLETE, null);
			
			
			entity.setEntitled(entitled);
			
			savedEntitledRegistrationDto = entitledRegistrationMapper.toDto(entity);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntitledRegistrationDto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledRegistrationDto inProgress(String serial, EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException {
		EntitledRegistrationDto savedEntitledRegistrationDto = null;
		try {
			EntitledRegistration entity = getIfValid(serial);
			entitledRegistrationWorkflowValidator.validate(entity, RequestInternalStatusEnum.INPROGRESS);
			
			Request request = requestService.changeStatus(entity.getRequest(), RequestInternalStatusEnum.INPROGRESS, entitledRegistrationChangeStatusRequest.getNote());
			entity.setRequest(request);
					
			Set<Entitled> entitleds = internalEntitledService.changeStatusByEntitledRegistrationId(entity.getId(), EntitledStatusEnum.INPROGRESS, null);
			entity.setEntitled(entitleds);
			
			savedEntitledRegistrationDto = entitledRegistrationMapper.toDto(entity);
			
			// send email With Report
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Request_Serial", savedEntitledRegistrationDto.getRequest().getSerial());
			parameters.put("CapacityDelegation_JobTitle", savedEntitledRegistrationDto.getCapacityDelegation().getJobTitle());
			File file = File.createTempFile("checkEntitleds", ".pdf"); 
			ReportGeneration.generateReportToFile("EntitledRegistrationReport", parameters, entitleds, file.getPath(), new Locale("ar"));
			
			mailUtil.sendEntitledsCheck(environment.getProperty("app.lawaffairs.email"), file);
			
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntitledRegistrationDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledRegistrationDto reject(String serial, EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException {
		EntitledRegistrationDto savedEntitledRegistrationDto = null;
		try {
			EntitledRegistration entity = getIfValid(serial);
			entitledRegistrationWorkflowValidator.validate(entity, RequestInternalStatusEnum.REJECTED);
			
			Request request = requestService.changeStatus(entity.getRequest(), RequestInternalStatusEnum.REJECTED, entitledRegistrationChangeStatusRequest.getNote());
			entity.setRequest(request);
			
			Set<Entitled> entitleds = internalEntitledService.changeStatusByEntitledRegistrationId(entity.getId(), EntitledStatusEnum.REJECTED, null);
			
			
			entity.setEntitled(entitleds);
			
			savedEntitledRegistrationDto = entitledRegistrationMapper.toDto(entity);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntitledRegistrationDto;
	}

}

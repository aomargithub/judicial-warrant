package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

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
import com.informatique.gov.judicialwarrant.service.InternalCapacityDelegationService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledService;
import com.informatique.gov.judicialwarrant.service.InternalOrganizationUnitService;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;
import com.informatique.gov.judicialwarrant.support.report.ReportGeneration;
import com.informatique.gov.judicialwarrant.support.security.JudicialWarrantGrantedAuthority;
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
	private InternalEntitledService entitledService;
	private InternalOrganizationUnitService organizationunitService;
	private InternalCapacityDelegationService capacityDelegationService;
	private EntitledRegistrationRepository entitledRegistrationRepository;
	private RequestRepository requestRepository;
	private EntitledRegistrationWorkflowValidator entitledRegistrationWorkflowValidator;
	private ModelMapper<EntitledRegistration, EntitledRegistrationDto, Long> entitledRegistrationMapper;
	private ModelMapper<EntitledRegistration, EntitledRegistrationDto, Long> entitledRegistrationForInternalMapper;
	
	List<JudicialWarrantGrantedAuthority> authorities;


	@Override
	public void generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response, String serial)
			throws JudicialWarrantException {
		try {
			List<EntitledDto> entitledDtos = entitledService.getAllByEntitledRegistrationSerial(serial);
			entitledDtos = entitledDtos == null ? Arrays.asList(new EntitledDto()) : entitledDtos;
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
			if(authentication.getAuthorities().stream().anyMatch(authorities::contains)) {
				entities = entitledRegistrationRepository.findAll();
				dtos = entitledRegistrationForInternalMapper.toDto(entities);
			}else {
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
		OrganizationUnit organizationUnit = organizationunitService.getByCurrentUser();
		Short organizationUnitId = organizationUnit.getId();
		
		EntitledRegistration entity = entitledRegistrationRepository.findByRequestSerialAndRequestOrganizationUnitId(serial, organizationUnitId);
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
			
			Set<Entitled> entitled = entitledService.changeStatusByEntitledRegistrationId(entity.getId(), EntitledStatusEnum.SUBMITED, null);
			
			
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
	public EntitledRegistrationDto incomplete(String serial, EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest)
			throws JudicialWarrantException {
		EntitledRegistrationDto savedEntitledRegistrationDto = null;
		try {
			EntitledRegistration entity = getIfValid(serial);
			entitledRegistrationWorkflowValidator.validate(entity, RequestInternalStatusEnum.INCOMPLETE);
			
			Request request = requestService.changeStatus(entity.getRequest(), RequestInternalStatusEnum.INCOMPLETE, entitledRegistrationChangeStatusRequest.getNote());
			entity.setRequest(request);
			
			Set<Entitled> entitled = entitledService.changeStatusByEntitledRegistrationId(entity.getId(), EntitledStatusEnum.SUBMITED, null);
			
			
			entity.setEntitled(entitled);
			
			savedEntitledRegistrationDto = entitledRegistrationMapper.toDto(entity);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntitledRegistrationDto;
	}

	/*@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse rejectRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			EntitledRegistrationWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.REJECTED);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.REJECTED, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<EntitledDto> candidates = candidateService.getByEntitledRegistrationId(request);
			candidates = candidateService.changeStatus(candidates, EntitledStatusEnum.REJECTED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse inprogressRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			EntitledRegistrationWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.INPROGRESS);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<EntitledDto> candidates = candidateService.getByEntitledRegistrationId(request);
			candidates = candidateService.changeStatus(candidates, EntitledStatusEnum.ACCEPTED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse inTrainingRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			EntitledRegistrationWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.INPROGRESS);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<EntitledDto> candidates = candidateService.getByEntitledRegistrationId(request);
			candidates = candidateService.changeStatus(candidates, EntitledStatusEnum.TRAINNING);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse passTrainingRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<EntitledDto> candidates = candidateService.getByEntitledRegistrationId(request);
			candidates = candidateService.changeStatus(candidates, EntitledStatusEnum.PASSED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse failTrainingRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<EntitledDto> candidates = candidateService.getByEntitledRegistrationId(request);
			candidates = candidateService.changeStatus(candidates, EntitledStatusEnum.FAILED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse issuedRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.CAPACITY_DELEGATION_ISSUED, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<EntitledDto> candidates = candidateService.getByEntitledRegistrationId(request);
			candidates = candidateService.changeStatus(candidates, EntitledStatusEnum.CARD_RECIEVED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}*/

}

package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.persistence.repository.CapacityDelegationRepository;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.service.CapacityDelegationService;
import com.informatique.gov.judicialwarrant.service.InternalOrganizationUnitService;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;
import com.informatique.gov.judicialwarrant.support.security.JudicialWarrantGrantedAuthority;
import com.informatique.gov.judicialwarrant.support.validator.CapacityDelegationWorkflowValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CapacityDelegationServiceImpl implements CapacityDelegationService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InternalRequestService requestService;
	private InternalOrganizationUnitService organizationunitService;
	@Qualifier("capacityDelegationMapper")
	private ModelMapper<CapacityDelegation, CapacityDelegationDto, Long> capacityDelegationMapper;
	@Qualifier("capacityDelegationForInternalMapper")
	private ModelMapper<CapacityDelegation, CapacityDelegationDto, Long> capacityDelegationForInternalMapper;
	private CapacityDelegationRepository capacityDelegationRepository;
	
	List<JudicialWarrantGrantedAuthority> authorities;


	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<CapacityDelegationDto> getAll(Authentication  authentication) throws JudicialWarrantException {
		List<CapacityDelegationDto> dtos = null;
		
		try {
			List<CapacityDelegation> entities = null;
			if(authentication.getAuthorities().stream().anyMatch(authorities::contains)) {
				entities = capacityDelegationRepository.findAll();
				dtos = capacityDelegationForInternalMapper.toDto(entities);
			}else {
				OrganizationUnit organizationUnit = organizationunitService.getByCurrentUser();
				entities = capacityDelegationRepository.findByRequestOrganizationUnitId(organizationUnit.getId());
				dtos = capacityDelegationMapper.toDto(entities);
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
			version = capacityDelegationRepository.findVersionByRequestSerial(serial);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public CapacityDelegationDto getBySerial(Authentication  authentication, String serial) throws JudicialWarrantException {
		
		CapacityDelegationDto dto = null;
		try {
			notNull(serial, "serial must be set");
			CapacityDelegation entity = null;
			if(authentication.getAuthorities().stream().anyMatch(authorities::contains)) {
				entity = capacityDelegationRepository.findByRequestSerial(serial);
				dto = capacityDelegationForInternalMapper.toDto(entity);
			}else {
				OrganizationUnit organizationUnit = organizationunitService.getByCurrentUser();
				entity = capacityDelegationRepository.findByRequestSerialAndRequestOrganizationUnitId(serial, organizationUnit.getId());
				dto = capacityDelegationMapper.toDto(entity);
			}
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public CapacityDelegationDto create(CapacityDelegationDto capacityDelegationDto) throws JudicialWarrantException {

		CapacityDelegationDto savedCapacityDelegationDto = null;

		try {
			notNull(capacityDelegationDto, "capacityDelegationDto must be set");
			
			CapacityDelegation capacityDelegation = capacityDelegationMapper.toNewEntity(capacityDelegationDto);
			Request request = requestService.create(RequestTypeEnum.CAPACITY_DELEGATION);
			
			capacityDelegation.setRequest(request);
			CapacityDelegation savedCapacityDelegation = capacityDelegationRepository.save(capacityDelegation);
			savedCapacityDelegationDto = capacityDelegationMapper.toDto(savedCapacityDelegation);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedCapacityDelegationDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CapacityDelegationDto update(String serial, CapacityDelegationDto capacityDelegationDto)
			throws JudicialWarrantException {
		CapacityDelegationDto savedCapacityDelegationDto = null;
		try {
			
			notNull(capacityDelegationDto, "capacityDelegationDto must be set");
			
			CapacityDelegation capacityDelegation = getIfValid(serial);
			
			CapacityDelegationWorkflowValidator.validateForUpdate(capacityDelegation);
			capacityDelegation.setJobTitle(capacityDelegationDto.getJobTitle());
			CapacityDelegation savedCapacityDelegation = capacityDelegationRepository.save(capacityDelegation);
			savedCapacityDelegationDto = capacityDelegationMapper.toDto(savedCapacityDelegation);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedCapacityDelegationDto;
	}
	
	private CapacityDelegation getIfValid(String serial) throws JudicialWarrantException {
		
		OrganizationUnit organizationUnit = organizationunitService.getByCurrentUser();
		Short organizationUnitId = organizationUnit.getId();
		CapacityDelegation capacityDelegation = capacityDelegationRepository.findByRequestSerialAndRequestOrganizationUnitId(serial, organizationUnitId);
		
		if(capacityDelegation == null) {
			throw new ResourceNotFoundException(serial);
		}
		
		return capacityDelegation;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CapacityDelegationDto submit(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest)
			throws JudicialWarrantException {
		CapacityDelegationDto savedCapacityDelegationDto = null;
		try {
			
			CapacityDelegation capacityDelegation = getIfValid(serial);
			
			CapacityDelegationWorkflowValidator.validate(capacityDelegation, RequestInternalStatusEnum.RECIEVED);
			
			capacityDelegation.setJobTitle(capacityDelegationChangeStatusRequest.getCapacityDelegation().getJobTitle());
			CapacityDelegation savedCapacityDelegation = capacityDelegationRepository.save(capacityDelegation);
			
			Request request = requestService.changeStatus(capacityDelegation.getRequest(), RequestInternalStatusEnum.RECIEVED,
					capacityDelegationChangeStatusRequest.getNote());
			
			savedCapacityDelegation.setRequest(request);
			
			savedCapacityDelegationDto = capacityDelegationMapper.toDto(savedCapacityDelegation);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedCapacityDelegationDto;
	}

}

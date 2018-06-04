package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;

public interface CapacityDelegationService extends Serializable {
	List<CapacityDelegationDto> getAll(Authentication  authentication) throws JudicialWarrantException;

	Short getVersionBySerial(String serial) throws JudicialWarrantException;
	
	CapacityDelegationDto getBySerial(Authentication  authentication, String serial) throws JudicialWarrantException;

	
	CapacityDelegationDto create(CapacityDelegationDto capacityDelegationDto) throws JudicialWarrantException;
	
	CapacityDelegationDto update(String serial, CapacityDelegationDto capacityDelegationDto) throws JudicialWarrantException;
	
	CapacityDelegationDto submit(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
}

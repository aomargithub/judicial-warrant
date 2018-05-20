package com.informatique.gov.judicialwarrant.rest.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.rest.request.CapacityDelegationChangeStatusRequest;

public interface CapacityDelegationHandler {
	ResponseEntity<List<CapacityDelegationDto>> getAll(Authentication authentication) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> getBySerial(Authentication authentication, String serial) throws JudicialWarrantException;
	
	
	ResponseEntity<CapacityDelegationDto> create(CapacityDelegationDto capacityDelegationDto) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> update(String serial, CapacityDelegationDto capacityDelegationDto, Short etag) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> submit(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> incomplete(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> reject(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> accept(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> lawAffairsReview(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> lawAffairsAccept(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> lawAffairsReject(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
	ResponseEntity<CapacityDelegationDto> issue(String serial, CapacityDelegationChangeStatusRequest capacityDelegationChangeStatusRequest) throws JudicialWarrantException;
	
}

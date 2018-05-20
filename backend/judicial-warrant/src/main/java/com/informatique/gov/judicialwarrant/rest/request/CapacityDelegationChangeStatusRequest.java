package com.informatique.gov.judicialwarrant.rest.request;

import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;

import lombok.Data;

@Data
public class CapacityDelegationChangeStatusRequest {

	private CapacityDelegationDto capacityDelegation;
	private String note;
}

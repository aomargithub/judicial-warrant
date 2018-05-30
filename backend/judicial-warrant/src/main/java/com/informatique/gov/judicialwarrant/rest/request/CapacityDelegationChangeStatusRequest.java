package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;

import lombok.Data;

@Data
public class CapacityDelegationChangeStatusRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CapacityDelegationDto capacityDelegation;
	private String note;
}

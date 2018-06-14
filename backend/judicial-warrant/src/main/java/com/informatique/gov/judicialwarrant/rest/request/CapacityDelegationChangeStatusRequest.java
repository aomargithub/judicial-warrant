package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.informatique.gov.judicialwarrant.rest.dto.CapacityDelegationDto;
import com.informatique.gov.judicialwarrant.support.validator.CapacityDelegationChangeStatusRequestV;

import lombok.Data;


@Data
public class CapacityDelegationChangeStatusRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698575712022936099L;
	/**
	 * 
	 */
	@NotNull
//	@CapacityDelegationChangeStatusRequestV
	private CapacityDelegationDto capacityDelegation;
	@NotNull
	private String note;
}

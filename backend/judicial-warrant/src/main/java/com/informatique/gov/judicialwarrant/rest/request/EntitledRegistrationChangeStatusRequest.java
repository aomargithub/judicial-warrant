package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;

import lombok.Data;

@Data
public class EntitledRegistrationChangeStatusRequest implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntitledRegistrationDto entitledRegistration;
	private String note;
}

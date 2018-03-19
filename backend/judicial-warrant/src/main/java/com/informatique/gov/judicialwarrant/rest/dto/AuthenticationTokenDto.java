package com.informatique.gov.judicialwarrant.rest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationTokenDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -284279827638874003L;
	
	@Getter
	private final String value;
	@Getter
	private final Integer maxInactiveInterval;

}

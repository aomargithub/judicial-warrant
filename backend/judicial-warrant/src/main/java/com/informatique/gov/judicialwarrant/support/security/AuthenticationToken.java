package com.informatique.gov.judicialwarrant.support.security;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationToken implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6115924916941604567L;
	
	@Getter
	private final String value;
	@Getter
	private final Integer maxInactiveInterval;

}

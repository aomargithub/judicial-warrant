package com.informatique.gov.judicialwarrant.support.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

public class JudicialWarrantGrantedAuthority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 667607029945851148L;
	private final String role;
	@Getter
	private final String ldapSecurityGroup;

	
	
	
	public JudicialWarrantGrantedAuthority(String role, String ldapSecurityGroup) {
		this.role = role;
		this.ldapSecurityGroup = ldapSecurityGroup;
	}


	@Override
	public String getAuthority() {

		return role;
	}
	
	

}

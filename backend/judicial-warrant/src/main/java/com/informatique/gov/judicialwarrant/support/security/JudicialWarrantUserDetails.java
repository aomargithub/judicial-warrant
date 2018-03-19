package com.informatique.gov.judicialwarrant.support.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;

import lombok.Data;

@Data
public class JudicialWarrantUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3817860817948767837L;
	
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private String englishName;
	private String arabicName;
	private String mobileNumber1;
	private String mobileNumber2;
	private String emailAddress;
	private OrganizationUnit organizationUnit;
	private Long civilId;
	private AuthenticationToken token;

}

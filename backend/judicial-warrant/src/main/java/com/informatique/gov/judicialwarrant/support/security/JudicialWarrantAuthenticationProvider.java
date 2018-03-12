package com.informatique.gov.judicialwarrant.support.security;

import java.io.Serializable;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JudicialWarrantAuthenticationProvider implements AuthenticationProvider, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1450799324327380603L;
	
	private UserDetailsService userDetailsService;
	private ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		

		Object principal = authentication.getPrincipal();
		UserDetails userDetails = null;

		Authentication adlapAuthentication = activeDirectoryLdapAuthenticationProvider.authenticate(authentication);
		if (adlapAuthentication == null || !adlapAuthentication.isAuthenticated()) {
			return null;
		}
		
		userDetails = userDetailsService.loadUserByUsername(principal.toString());

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), adlapAuthentication.getCredentials(), userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(userDetails);

		return usernamePasswordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authenticationClazz) {
		return authenticationClazz.equals(UsernamePasswordAuthenticationToken.class);
	}

}

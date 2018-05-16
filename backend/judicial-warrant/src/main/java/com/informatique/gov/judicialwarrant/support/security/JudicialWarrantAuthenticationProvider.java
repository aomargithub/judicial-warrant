package com.informatique.gov.judicialwarrant.support.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.stereotype.Component;
import com.informatique.gov.judicialwarrant.support.dataenum.UserTypeEnum;
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
	
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Object principal = authentication.getPrincipal();
		UserDetails userDetails = null;
		JudicialWarrantUserDetails judicialWarrantUserDetails = null;
		Authentication adlapAuthentication=null;
		Collection<? extends GrantedAuthority> authorities=null;

		userDetails = userDetailsService.loadUserByUsername(principal.toString().trim());

		if (userDetails == null) {
			throw new UsernameNotFoundException(principal.toString());
		}
		if (userDetails != null) {
			judicialWarrantUserDetails = (JudicialWarrantUserDetails) userDetails;

			if (judicialWarrantUserDetails.getUserType().getCode().equalsIgnoreCase(UserTypeEnum.INTERNAL.getCode())) {
				
				adlapAuthentication = activeDirectoryLdapAuthenticationProvider
						.authenticate(authentication);
				if (adlapAuthentication == null || !adlapAuthentication.isAuthenticated()) {
					return null;
				}
				authorities= compareRoles(adlapAuthentication.getAuthorities()
						,userDetails.getAuthorities());
				
			}

			else if (judicialWarrantUserDetails.getUserType().getCode().equalsIgnoreCase(UserTypeEnum.EXTERNAL.getCode())) {
				String password =
						authentication.getCredentials().toString().trim();
				if (!passwordEncoder.matches(password, userDetails.getPassword().trim())) {
					return null;
				}
				authorities=userDetails.getAuthorities();
			}

		}

		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), authentication.getCredentials(),authorities);
		usernamePasswordAuthenticationToken.setDetails(userDetails);

		return usernamePasswordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authenticationClazz) {
		return authenticationClazz.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	private Collection<? extends GrantedAuthority> compareRoles(Collection<? extends GrantedAuthority> ldapAuthorites
			,Collection<? extends GrantedAuthority> databaseAuthorites){
				
		Collection<GrantedAuthority> intersectAuthorities = new ArrayList<>();
		  Iterator<? extends GrantedAuthority> ldapIterator =  ldapAuthorites.iterator();
		  for (GrantedAuthority authority:databaseAuthorites) {
		    if (authority.equals(ldapIterator.next())) {
		    	intersectAuthorities.add(authority);
		    }
		  }
		  
		return intersectAuthorities;
		
		
	}

}

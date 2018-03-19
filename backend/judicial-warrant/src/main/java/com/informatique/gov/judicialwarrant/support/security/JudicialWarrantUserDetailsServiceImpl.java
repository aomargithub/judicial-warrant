package com.informatique.gov.judicialwarrant.support.security;

import static org.springframework.util.Assert.notNull;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.service.InternalUserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JudicialWarrantUserDetailsServiceImpl implements UserDetailsService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2167322879669809169L;
	private InternalUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		JudicialWarrantUserDetails userDetails = null;
		User user = null;
		try {
			notNull(username, "username must be set");
			user = userService.getByLoginName(username);
			
			if(user == null) {
				throw new UsernameNotFoundException(username);
			}
			
			userDetails = toUserDetails(user);
			
		} catch (JudicialWarrantInternalException e) {
			throw new UsernameNotFoundException(username, e);
		} catch (UsernameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new UsernameNotFoundException(username, new JudicialWarrantInternalException(e));
		}
		return userDetails;
	}

	private JudicialWarrantUserDetails toUserDetails(User user) {
		
		notNull(user, "user must be set");
		
		JudicialWarrantUserDetails userDetails = new JudicialWarrantUserDetails();
		
		userDetails.setArabicName(user.getArabicName());
		userDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(user.getRole().getCode())));
		userDetails.setCivilId(user.getCivilId());
		userDetails.setEmailAddress(user.getEmailAddress());
		userDetails.setEnabled(user.getIsActive());
		userDetails.setEnglishName(user.getEnglishName());
		userDetails.setMobileNumber1(user.getMobileNumber1());
		userDetails.setMobileNumber2(user.getMobileNumber2());
		userDetails.setOrganizationUnit(user.getOrganizationUnit());
		userDetails.setUsername(user.getLoginName());
		// token to be set later in more convenient place where we can get the session id easily.
		return userDetails;
	}
}

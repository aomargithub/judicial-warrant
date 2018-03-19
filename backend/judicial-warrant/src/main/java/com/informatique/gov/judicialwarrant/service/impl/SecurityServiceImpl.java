package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.AuthenticationTokenDto;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserDetailsDto;
import com.informatique.gov.judicialwarrant.service.SecurityService;
import com.informatique.gov.judicialwarrant.support.security.AuthenticationToken;
import com.informatique.gov.judicialwarrant.support.security.JudicialWarrantUserDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5889314472325367932L;
	private Environment environment;
	
	@Override
	public UserDetailsDto getUserDetails(HttpSession session) throws JudicialWarrantException {
		UserDetailsDto userDetailsDto = null;
		try {
			Principal principal = SecurityContextHolder.getContext().getAuthentication();
			UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)principal;
			JudicialWarrantUserDetails userDetails = (JudicialWarrantUserDetails)authenticationToken.getDetails();
			userDetails.setToken(new AuthenticationToken(session.getId(), Integer.parseInt(environment.getRequiredProperty("app.security.max-inactive-interval"))));
			userDetailsDto = toUserDetailsDto(userDetails);
		}catch(Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
		return userDetailsDto;
	}
	
	@Override
	public String getPrincipal() throws JudicialWarrantInternalException {
		String username = null;
		try {
			Principal principal = SecurityContextHolder.getContext().getAuthentication();
			UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)principal;
			username = authenticationToken.getPrincipal().toString();
		}catch(Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
		return username;
	}
	
	private UserDetailsDto toUserDetailsDto(JudicialWarrantUserDetails userDetails) {
		
		notNull(userDetails, "userDetails must be set");
		
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setArabicName(userDetails.getArabicName());
		userDetailsDto.setCivilId(userDetails.getCivilId());
		userDetailsDto.setEmailAddress(userDetails.getEmailAddress());
		userDetailsDto.setEnglishName(userDetails.getEnglishName());
		userDetailsDto.setMobileNumber1(userDetails.getMobileNumber1());
		userDetailsDto.setMobileNumber2(userDetails.getMobileNumber2());
		userDetailsDto.setOrganizationUnit(toDto(userDetails.getOrganizationUnit()));
		userDetailsDto.setRole(userDetails.getAuthorities().iterator().next().getAuthority());
		userDetailsDto.setToken(new AuthenticationTokenDto(userDetails.getToken().getValue(), userDetails.getToken().getMaxInactiveInterval()));
		return userDetailsDto;
	}
	
	private OrganizationUnitDto toDto(OrganizationUnit entity) {
		OrganizationUnitDto dto = null;
		
		if(entity != null) {
			dto = new OrganizationUnitDto();
			dto.setArabicName(entity.getArabicName());
			dto.setEnglishName(entity.getEnglishName());
			dto.setId(entity.getId());
		}
		
		return dto;
	}

}

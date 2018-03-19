package com.informatique.gov.judicialwarrant.rest.handler.impl;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.UserDetailsDto;
import com.informatique.gov.judicialwarrant.rest.handler.LoginHandler;
import com.informatique.gov.judicialwarrant.service.SecurityService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LoginHandlerImpl implements LoginHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3671575528463487897L;
	
	private SecurityService securityService;
	
	@Override
	public ResponseEntity<UserDetailsDto> getUserDetails(HttpSession session) throws JudicialWarrantException {
		
		ResponseEntity<UserDetailsDto> response = null;
		
		try {
			UserDetailsDto userDetailsDto = securityService.getUserDetails(session);
			response = ResponseEntity.ok(userDetailsDto);
		}catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}

}

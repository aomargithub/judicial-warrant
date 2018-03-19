package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.UserDetailsDto;

public interface SecurityService extends Serializable{

	UserDetailsDto getUserDetails(HttpSession session) throws JudicialWarrantException;

	String getPrincipal() throws JudicialWarrantInternalException;

}

package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.UserDetailsDto;

public interface LoginHandler extends Serializable{

	ResponseEntity<UserDetailsDto> getUserDetails(HttpSession session) throws JudicialWarrantException;

}

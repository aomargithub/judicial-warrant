package com.informatique.gov.judicialwarrant.rest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.response.LoginResponse;
import com.informatique.gov.judicialwarrant.service.SecurityService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
	
	private SecurityService securityService;
	
	@GetMapping
	public LoginResponse login(HttpSession session) throws JudicialWarrantException {
		return new LoginResponse(securityService.getUserDetails(session));
	}
}

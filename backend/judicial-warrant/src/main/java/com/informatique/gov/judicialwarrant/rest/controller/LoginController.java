package com.informatique.gov.judicialwarrant.rest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.LoginHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
	
	private LoginHandler loginHandler;
	
	@GetMapping
	public ResponseEntity<?> login(HttpSession session) throws JudicialWarrantException {
		return loginHandler.getUserDetails(session);
	}
}

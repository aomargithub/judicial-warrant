package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.RoleHandler;
import com.informatique.gov.judicialwarrant.support.validations.RoleValidator;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController implements Serializable{

	private RoleHandler roleHandler;
	
	private RoleValidator roleDtoValidator;
	
	private static final long serialVersionUID = 1L;
	
	
	
	@InitBinder("roleDto")
	private void roleInitBinder(WebDataBinder binder) {
		binder.addValidators(roleDtoValidator);
	}
	
    @GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return roleHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Byte id
			) throws JudicialWarrantException {
		return roleHandler.getById(id);
	}
	
	@GetMapping(params = {"isInternal"})
	public ResponseEntity<?> getByIsInternal(@RequestParam Boolean isInternal) throws JudicialWarrantException {
		return roleHandler.getByIsInternal(isInternal);
	}


}

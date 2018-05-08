package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.rest.handler.UserHandler;
import com.informatique.gov.judicialwarrant.support.validations.UserDtoValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7427269755961905168L;
	
    private UserHandler userHandler;
    private UserDtoValidator userValidator;

    @InitBinder("userDto")
	private void roleInitBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
    @GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return userHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id,@RequestHeader(name = "If-None-Match", required = false) Short eTag
			) throws JudicialWarrantException {
		return userHandler.getById(id,eTag);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserDto dto) throws JudicialWarrantException {
		return userHandler.save(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody UserDto dto, @PathVariable Integer id,
			@RequestHeader(name = "If-Match", required = false) Short eTag
			) throws JudicialWarrantException {
		return userHandler.update(dto,id,eTag);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) throws JudicialWarrantException {
		return userHandler.delete(id);
	}

}
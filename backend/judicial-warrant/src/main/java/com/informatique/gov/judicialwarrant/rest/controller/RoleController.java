package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.RoleDto;
import com.informatique.gov.judicialwarrant.rest.handler.RoleHandler;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController implements Serializable{

	private RoleHandler roleHandler;
	
	private static final long serialVersionUID = 1L;
    @GetMapping
	public ResponseEntity<?> getAll() throws JudicialWarrantException {
		return roleHandler.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Byte id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return roleHandler.getById(id, eTag);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody RoleDto roleDto) throws JudicialWarrantException {
		return roleHandler.save(roleDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody RoleDto roleDto, @PathVariable Byte id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return roleHandler.update(roleDto, id, eTag);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Byte id) throws JudicialWarrantException {
		return roleHandler.delete(id);
	}
}

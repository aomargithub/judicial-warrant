package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.handler.OrganizationUnitHandler;
import com.informatique.gov.judicialwarrant.rest.request.OrganizationUnitSaveRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/organizationUnits")
public class OrganizationUnitController implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 257822236227955351L;
	
	private OrganizationUnitHandler organizationUnitHandler;
	
	@GetMapping
    public ResponseEntity<?> getAll() throws JudicialWarrantException {
        return organizationUnitHandler.getAll();
    }
	
	@GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Short id, @RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
        return organizationUnitHandler.getById(id, eTag);
    }
	
	@PostMapping
    public ResponseEntity<?> save(@RequestBody OrganizationUnitDto organizationUnit) throws JudicialWarrantException {
        return organizationUnitHandler.save(organizationUnit);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrganizationUnitSaveRequest organizationUnitSaveRequest, @PathVariable Short id, @RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
        return organizationUnitHandler.update(organizationUnitSaveRequest, id, eTag);
    }

}

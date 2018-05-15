package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.RequestTypeHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/requestTypes")
public class RequestTypeController implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 7701627550236801076L;
	private RequestTypeHandler requestTypeHandler;
	
	@GetMapping
    public ResponseEntity<?> getAll() throws JudicialWarrantException {
        return requestTypeHandler.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable String code) throws JudicialWarrantException {
        return requestTypeHandler.getByCode(code);
    }
    
    @GetMapping(params = {"isActive"})
    public ResponseEntity<?> getByIsActive(@RequestParam Boolean isActive) throws JudicialWarrantException {
    	return requestTypeHandler.getByIsActive(isActive);
    }
}

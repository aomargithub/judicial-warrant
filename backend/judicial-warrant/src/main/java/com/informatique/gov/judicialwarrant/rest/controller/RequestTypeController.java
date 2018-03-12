package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.RequestTypeHandler;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;

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
    public RestResponse getAll() throws JudicialWarrantException {
        return requestTypeHandler.getAll();
    }

    @GetMapping("/{code}")
    public RestResponse getByCode(@PathVariable String code) throws JudicialWarrantException {
        return requestTypeHandler.getByCode(code);
    }
    
    @GetMapping(params = {"isActive"})
    public RestResponse getByIsActive(@RequestParam Boolean isActive) throws JudicialWarrantException {
    	return requestTypeHandler.getByIsActive(isActive);
    }
}

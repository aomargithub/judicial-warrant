package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.NationalityHandler;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/nationalities")
public class NationalityController implements Serializable{
	/**
	* 
	 */
	private static final long serialVersionUID = 7701627550236801076L;
	private NationalityHandler nationalityHandler;
	
	@GetMapping
    public RestResponse getAll() throws JudicialWarrantException {
        return nationalityHandler.getAll();
    }

    @GetMapping("/{code}")
    public RestResponse getByCode(@PathVariable String code) throws JudicialWarrantException {
        return nationalityHandler.getByCode(code);
    }
    
}

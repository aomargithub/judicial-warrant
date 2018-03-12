package com.informatique.gov.judicialwarrant.rest.controller;



import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.LocaleHandler;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/locales")
public class LocaleController implements Serializable{

    private static final long serialVersionUID = 6340171444962304661L;
    private LocaleHandler localeHandler;


    @GetMapping
    public RestResponse getAll() throws JudicialWarrantException {
        return localeHandler.getAll();
    }

    @GetMapping("/{code}")
    public RestResponse getByCode(@PathVariable String code) throws JudicialWarrantException {
        return localeHandler.getByCode(code);
    }

    @GetMapping(params = {"isActive"})
    public RestResponse getByIsActive(@RequestParam Boolean isActive) throws JudicialWarrantException {
    	return localeHandler.getByIsActive(isActive);
    }
}

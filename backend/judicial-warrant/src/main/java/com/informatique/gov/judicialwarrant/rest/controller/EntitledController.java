package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entitleds")
public class EntitledController implements Serializable {

	
	private EntitledHandler entitledHandler ;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
    
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws JudicialWarrantException {
		return entitledHandler.delete(id);
	}

}

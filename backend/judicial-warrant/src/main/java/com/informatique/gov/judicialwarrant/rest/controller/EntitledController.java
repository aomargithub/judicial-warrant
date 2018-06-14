package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
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

	 	@GetMapping
		public ResponseEntity<?> getAll(@RequestParam("entiteldRegistrationSerial") String entiteldRegistrationSerial) throws JudicialWarrantException {
			return entitledHandler.getAllByEntitledRegistrationSerial(entiteldRegistrationSerial);
		}

		@GetMapping(path = "/{id}")
		public ResponseEntity<?> getById(@PathVariable Long id,
				@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
			return entitledHandler.getById(id, eTag);
		}

		@PostMapping
		public ResponseEntity<?> save(@Valid @RequestBody EntitledDto entitledDto) throws JudicialWarrantException {
			return entitledHandler.save(entitledDto);
		}

		@PutMapping("/{id}")
		public ResponseEntity<?> update(@Valid @RequestBody EntitledDto entitledDto, @PathVariable Long id,
				@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
			return entitledHandler.update(entitledDto, id, eTag);
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) throws JudicialWarrantException {
			return entitledHandler.delete(id);
		}

}

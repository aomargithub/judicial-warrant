package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface EntitledHandler extends Serializable {

	ResponseEntity<Void> delete(Long id) throws JudicialWarrantException;

}

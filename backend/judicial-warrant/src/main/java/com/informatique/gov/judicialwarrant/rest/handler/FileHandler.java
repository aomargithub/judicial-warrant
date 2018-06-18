package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface FileHandler extends Serializable {
	ResponseEntity<String> upload(MultipartFile multipartFile) throws JudicialWarrantException;
}

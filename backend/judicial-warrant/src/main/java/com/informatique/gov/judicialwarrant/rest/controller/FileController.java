package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.handler.FileHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8218542953772806708L;

	private FileHandler fileHandler;

	@PostMapping
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws JudicialWarrantException {
		return fileHandler.upload(file);
	}


}

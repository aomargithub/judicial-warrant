package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.junit.Assert.assertNotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.handler.FileHandler;
import com.informatique.gov.judicialwarrant.service.FileService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FileHandlerImpl implements FileHandler {
	private FileService fileService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ResponseEntity<String> upload(MultipartFile multipartFile) throws JudicialWarrantException {
		ResponseEntity<String> response = null;
		try {
			assertNotNull(multipartFile);
			String fileId = fileService.upload(multipartFile);
			response = ResponseEntity.ok(fileId);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

}

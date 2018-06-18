package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface FileService extends Serializable{
	String upload(final MultipartFile multipartFile) throws JudicialWarrantException;
}

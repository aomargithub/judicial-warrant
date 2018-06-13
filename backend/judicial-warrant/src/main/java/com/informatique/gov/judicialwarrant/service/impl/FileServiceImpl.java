package com.informatique.gov.judicialwarrant.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.service.FileService;
import com.informatique.gov.judicialwarrant.support.integration.contentmanger.ContentManager;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ContentManager contentManager;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String upload(MultipartFile multipartFile) throws JudicialWarrantException {
		try {
			return contentManager.checkin(null, multipartFile);
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}

}

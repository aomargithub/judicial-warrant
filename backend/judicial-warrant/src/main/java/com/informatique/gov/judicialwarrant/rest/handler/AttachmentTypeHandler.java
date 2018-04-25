package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;

public interface AttachmentTypeHandler extends Serializable {
	ResponseEntity<List<AttachmentTypeDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<AttachmentTypeDto> getById(Long id, Short etag) throws JudicialWarrantException;

	ResponseEntity<AttachmentTypeDto> update(AttachmentTypeDto attachmentTypeDto, Long id, Short etag)
			throws JudicialWarrantException;

	ResponseEntity<AttachmentTypeDto> save(AttachmentTypeDto attachmentTypeDto)
			throws JudicialWarrantException;

	ResponseEntity<Void> delete(Long id) throws JudicialWarrantException;
}

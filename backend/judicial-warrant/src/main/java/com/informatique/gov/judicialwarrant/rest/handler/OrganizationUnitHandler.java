package com.informatique.gov.judicialwarrant.rest.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;

public interface OrganizationUnitHandler extends Serializable {

	ResponseEntity<List<OrganizationUnitDto>> getAll() throws JudicialWarrantException;

	ResponseEntity<OrganizationUnitDto> getById(Short id, Short etag) throws JudicialWarrantException;

	ResponseEntity<OrganizationUnitDto> update(OrganizationUnitDto organizationUnitdto, Short id, Short etag)
			throws JudicialWarrantException;

	ResponseEntity<OrganizationUnitDto> save(OrganizationUnitDto organizationUnitdto) throws JudicialWarrantException;

	ResponseEntity<Void> delete(Short id) throws JudicialWarrantException;
	ResponseEntity<List<OrganizationUnitDto>> getByIsInternal(Boolean isInternal)throws JudicialWarrantException;

}

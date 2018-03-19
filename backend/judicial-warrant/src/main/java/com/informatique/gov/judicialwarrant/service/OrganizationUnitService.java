package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;

public interface OrganizationUnitService extends Serializable{

	List<OrganizationUnitDto> getAll() throws JudicialWarrantException;

	OrganizationUnitDto save(final OrganizationUnitDto dto) throws JudicialWarrantException;

	OrganizationUnitDto getById(Short id) throws JudicialWarrantException;

//	OrganizationUnitDto getByIdIfModified(Short id, Short version) throws JudicialWarrantException;

	OrganizationUnitDto update(OrganizationUnitDto dto) throws JudicialWarrantException;

//	OrganizationUnitDto updateIfNotModified(OrganizationUnitDto dto, Short id) throws JudicialWarrantException;

	Short getVersionById(Short id) throws JudicialWarrantException;

	void delete(Short id) throws JudicialWarrantException;

}

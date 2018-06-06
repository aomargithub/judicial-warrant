package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;

public interface EntitledService extends Serializable{

	EntitledDto getById(Long id) throws JudicialWarrantException;

	List<EntitledDto> getAllByEntitledRegistrationSerial(String serial) throws JudicialWarrantException;

	Integer deleteByEntitledRegistrationId(Long entitledRegistrationId) throws JudicialWarrantException;

	void deleteById(Long id) throws JudicialWarrantException;

	

	Set<EntitledDto> changeStatus(Set<EntitledDto> dtos, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException;

	EntitledDto changeStatus(EntitledDto dto, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException;	

}

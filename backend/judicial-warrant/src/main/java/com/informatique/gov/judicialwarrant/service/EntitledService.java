package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.Set;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;

public interface EntitledService extends Serializable{

	EntitledDto getById(Long id) throws JudicialWarrantException;
	
	Short getVersionById(Long id) throws JudicialWarrantException;
	
	EntitledDto save(EntitledDto entitledDto) throws JudicialWarrantException;
	
	EntitledDto update(EntitledDto entitledDto) throws JudicialWarrantException;

	Set<EntitledDto> getAllByEntitledRegistrationSerial(String serial) throws JudicialWarrantException;

	Integer deleteByEntitledRegistrationId(Long entitledRegistrationId) throws JudicialWarrantException;

	void deleteById(Long id) throws JudicialWarrantException;

	EntitledDto accept(String serial, Long id, String note) throws JudicialWarrantException;
	
	Set<EntitledDto> acceptAll(String serial,String note) throws JudicialWarrantException;
	
	EntitledDto reject(String serial, Long id, String note) throws JudicialWarrantException;
	
	EntitledDto inTraining(String serial, Long id, String note) throws JudicialWarrantException;
	
	EntitledDto passed(String serial, Long id, String note) throws JudicialWarrantException;
	
	EntitledDto fail(String serial, Long id, String note) throws JudicialWarrantException;
	
	EntitledDto cardRecieved(String serial, Long id, String note) throws JudicialWarrantException;

	Set<EntitledDto> changeStatus(Set<EntitledDto> dtos, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException;

	EntitledDto changeStatus(EntitledDto dto, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException;	

}

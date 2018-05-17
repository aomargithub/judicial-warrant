package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.Set;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.support.dataenum.CandidateStatusEnum;

public interface CandidateService extends Serializable{

	CandidateDto save(final CandidateDto dto, CandidateStatusEnum candidateStatusEnum) throws JudicialWarrantException;
	
	CandidateDto save(final CandidateDto dto) throws JudicialWarrantException;
	
	Set<CandidateDto> save(final Set<CandidateDto> dto, RequestDto requestDto) throws JudicialWarrantException;

	CandidateDto getById(Long id) throws JudicialWarrantException;

	CandidateDto update(CandidateDto dto) throws JudicialWarrantException;
	
	CandidateDto changeStatus(CandidateDto dto, CandidateStatusEnum candidateStatusEnum) throws JudicialWarrantException;

	Set<CandidateDto> changeStatus(Set<CandidateDto> dtos, CandidateStatusEnum candidateStatusEnum) throws JudicialWarrantException;
	
	Short getVersionById(Long id) throws JudicialWarrantException;
	
	void deleteCandidatesByRequest(Request request) throws JudicialWarrantException;
	
	Set<CandidateDto> getCandidatesByRequest(Request request) throws JudicialWarrantException;
	

}

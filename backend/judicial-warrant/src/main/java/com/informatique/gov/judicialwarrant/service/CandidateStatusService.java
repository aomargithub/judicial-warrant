package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.List;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateStatusDto;

public interface CandidateStatusService extends Serializable{
	List<CandidateStatusDto> getAll() throws JudicialWarrantException;

	CandidateStatusDto getById(Byte id) throws JudicialWarrantException;

	public CandidateStatusDto getByCode(String code) throws JudicialWarrantException;
	
}

package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.CandidateStatus;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.CandidateStatusRepository;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateStatusDto;
import com.informatique.gov.judicialwarrant.service.CandidateStatusService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CandidateStatusServiceImpl implements CandidateStatusService {
	private CandidateStatusRepository candidateStatusRepository;
	private ModelMapper<CandidateStatus, CandidateStatusDto, Byte> candidateStatusMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<CandidateStatusDto> getAll() throws JudicialWarrantException {
		List<CandidateStatusDto> dtos = null ;
		try {
			List<CandidateStatus> entities = candidateStatusRepository.findAll();
			dtos = candidateStatusMapper.toDto(entities);
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public CandidateStatusDto getById(Byte id) throws JudicialWarrantException {
		CandidateStatusDto dto = null;
		try {
			notNull(id, "id must be set");
			
			CandidateStatus entity = candidateStatusRepository.findById(id).get();
			dto = candidateStatusMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public CandidateStatusDto getByCode(String code) throws JudicialWarrantException {
		CandidateStatus entity = null;
		CandidateStatusDto dto = null;
		try {
			notNull(code, "code must be set");
			entity = candidateStatusRepository.findByCode(code);
			dto = candidateStatusMapper.toDto(entity);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

}

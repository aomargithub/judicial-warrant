package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.EntitledStatus;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledStatusRepository;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledStatusDto;
import com.informatique.gov.judicialwarrant.service.EntitledStatusService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CandidateStatusServiceImpl implements EntitledStatusService {
	private EntitledStatusRepository candidateStatusRepository;
	private ModelMapper<EntitledStatus, EntitledStatusDto, Short> candidateStatusMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<EntitledStatusDto> getAll() throws JudicialWarrantException {
		List<EntitledStatusDto> dtos = null ;
		try {
			List<EntitledStatus> entities = candidateStatusRepository.findAll();
			dtos = candidateStatusMapper.toDto(entities);
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public EntitledStatusDto getById(Byte id) throws JudicialWarrantException {
		EntitledStatusDto dto = null;
		try {
			notNull(id, "id must be set");
			
			EntitledStatus entity = candidateStatusRepository.findById(id).get();
			dto = candidateStatusMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public EntitledStatusDto getByCode(String code) throws JudicialWarrantException {
		EntitledStatus entity = null;
		EntitledStatusDto dto = null;
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

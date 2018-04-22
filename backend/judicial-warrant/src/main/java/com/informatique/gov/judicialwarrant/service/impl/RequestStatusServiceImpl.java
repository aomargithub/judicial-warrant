package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.RequestStatus;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestStatusRepository;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;
import com.informatique.gov.judicialwarrant.service.RequestStatusService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestStatusServiceImpl implements RequestStatusService {
	private RequestStatusRepository requestStatusRepository;
	private ModelMapper<RequestStatus, RequestStatusDto, Byte> requestStatusMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<RequestStatusDto> getAll() throws JudicialWarrantException {
		List<RequestStatusDto> dtos = null;
		try {
			List<RequestStatus> entities = requestStatusRepository.findAll();
			dtos = requestStatusMapper.toDto(entities);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public RequestStatusDto getById(Byte id) throws JudicialWarrantException {
		RequestStatusDto dto = null;
		try {
			notNull(id, "id must be set");

			RequestStatus entity = requestStatusRepository.findById(id).get();
			dto = requestStatusMapper.toDto(entity);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public RequestStatusDto getByCode(String code) throws JudicialWarrantException {
		RequestStatus entity = null;
		RequestStatusDto dto = null;
		try {
			notNull(code, "code must be set");
			entity = requestStatusRepository.findByCode(code);
			dto = requestStatusMapper.toDto(entity);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

}

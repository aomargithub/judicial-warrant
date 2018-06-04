package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.RequestTypeAttachmentType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestTypeAttachmentTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeAttachmentTypeDto;
import com.informatique.gov.judicialwarrant.service.RequestTypeAttachmentTypeService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestTypeAttachmentTypeServiceImpl implements RequestTypeAttachmentTypeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6476773292451702937L;
	private RequestTypeAttachmentTypeRepository requestTypeAttachmentTypeRepository;
	private ModelMapper<RequestTypeAttachmentType, RequestTypeAttachmentTypeDto, Short> requestTypeAttachmentTypeMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public RequestTypeAttachmentTypeDto getById(Short id) throws JudicialWarrantException {
		RequestTypeAttachmentType entity = null;
		RequestTypeAttachmentTypeDto dto = null;
		try {
			notNull(id, "id must be set");
			entity = requestTypeAttachmentTypeRepository.getOne(id);
			dto = requestTypeAttachmentTypeMapper.toDto(entity);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Short getVersionById(Short id) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(id, "id must be set");
			version = requestTypeAttachmentTypeRepository.getVersionById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<RequestTypeAttachmentTypeDto> getByRequestTypeId(Byte id) throws JudicialWarrantException {
		List<RequestTypeAttachmentType> requestTypeAttachmentTypes = null;
		List<RequestTypeAttachmentTypeDto> requestTypeAttachmentTypeDtos = null;
		try {
			notNull(id, "id must be set");
			requestTypeAttachmentTypes = requestTypeAttachmentTypeRepository.findByRequestTypeId(id);
			requestTypeAttachmentTypeDtos = requestTypeAttachmentTypeMapper.toDto(requestTypeAttachmentTypes);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return requestTypeAttachmentTypeDtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public RequestTypeAttachmentTypeDto save(RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto)
			throws JudicialWarrantException {
		RequestTypeAttachmentType requestTypeAttachmentType = null;
		try {
			notNull(requestTypeAttachmentTypeDto, "dto must be set");
			requestTypeAttachmentType = requestTypeAttachmentTypeMapper.toEntity(requestTypeAttachmentTypeDto);
			requestTypeAttachmentType = requestTypeAttachmentTypeRepository.save(requestTypeAttachmentType);
			requestTypeAttachmentTypeDto = requestTypeAttachmentTypeMapper.toDto(requestTypeAttachmentType);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return requestTypeAttachmentTypeDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public RequestTypeAttachmentTypeDto update(RequestTypeAttachmentTypeDto requestTypeAttachmentTypeDto)
			throws JudicialWarrantException {
		RequestTypeAttachmentType requestTypeAttachmentType = null;
		try {
			notNull(requestTypeAttachmentTypeDto, "dto must be set");
			requestTypeAttachmentType = requestTypeAttachmentTypeMapper.toEntity(requestTypeAttachmentTypeDto);
			requestTypeAttachmentType = requestTypeAttachmentTypeRepository.save(requestTypeAttachmentType);
			requestTypeAttachmentTypeDto = requestTypeAttachmentTypeMapper.toDto(requestTypeAttachmentType);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return requestTypeAttachmentTypeDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Short id) throws JudicialWarrantException {
		try {
			notNull(id, "id must be set");
			requestTypeAttachmentTypeRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

	}

}

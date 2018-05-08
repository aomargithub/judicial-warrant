package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.AttachmentTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.service.AttachmentTypeService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AttachmentTypeServiceImpl implements AttachmentTypeService {
	private AttachmentTypeRepository attachmentTypeRepository;
	private ModelMapper<AttachmentType, AttachmentTypeDto, Long> attachmentTypeMapper;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<AttachmentTypeDto> getAll() throws JudicialWarrantException {
		List<AttachmentTypeDto> dtos = null ;
		try {
			List<AttachmentType> entities = attachmentTypeRepository.findAll();
			dtos = attachmentTypeMapper.toDto(entities);
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AttachmentTypeDto save(AttachmentTypeDto dto) throws JudicialWarrantException {
		AttachmentTypeDto savedDto = null;

		try {
			notNull(dto, "dto must be set");

			AttachmentType entiry = attachmentTypeMapper.toNewEntity(dto);						
			
			entiry = attachmentTypeRepository.save(entiry);
			
			savedDto = attachmentTypeMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;	
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public AttachmentTypeDto getById(Long id) throws JudicialWarrantException {
		AttachmentTypeDto dto = null;
		try {
			notNull(id, "id must be set");
			
			AttachmentType entity = attachmentTypeRepository.findById(id).get();
			dto = attachmentTypeMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AttachmentTypeDto update(AttachmentTypeDto dto) throws JudicialWarrantException {
		AttachmentTypeDto savedDto = null;

		try {
			notNull(dto, "dto must be set");			

			AttachmentType entiry = attachmentTypeMapper.toEntity(dto);

			entiry = attachmentTypeRepository.save(entiry);
			
			savedDto = attachmentTypeMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Short getVersionById(Long id) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(id, "id must be set");
			version = attachmentTypeRepository.findVersionById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) throws JudicialWarrantException {
		try {
			notNull(id, "id must be set");
			attachmentTypeRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

	}

}

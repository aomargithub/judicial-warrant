package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.CandidateAttachment;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.CandidateAttachmentRepository;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateAttachmentDto;
import com.informatique.gov.judicialwarrant.service.CandidateAttachmentService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CandidateAttachmentServiceImpl implements CandidateAttachmentService {
	private CandidateAttachmentRepository candidateAttachmentRepository;
	private ModelMapper<CandidateAttachment, CandidateAttachmentDto, Long> candidateAttachmentMapper;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<CandidateAttachmentDto> getAll() throws JudicialWarrantException {
		List<CandidateAttachmentDto> dtos = null ;
		try {
			List<CandidateAttachment> entities = candidateAttachmentRepository.findAll();
			dtos = candidateAttachmentMapper.toDto(entities);
		}
		catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public CandidateAttachmentDto save(CandidateAttachmentDto dto) throws JudicialWarrantException {
		CandidateAttachmentDto savedDto = null;

		try {
			notNull(dto, "dto must be set");

			CandidateAttachment entiry = candidateAttachmentMapper.toNewEntity(dto);
			
			entiry = candidateAttachmentRepository.save(entiry);
			
			savedDto = candidateAttachmentMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;	
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public CandidateAttachmentDto getById(Long id) throws JudicialWarrantException {
		CandidateAttachmentDto dto = null;
		try {
			notNull(id, "id must be set");
			
			CandidateAttachment entity = candidateAttachmentRepository.findById(id).get();
			dto = candidateAttachmentMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CandidateAttachmentDto update(CandidateAttachmentDto dto) throws JudicialWarrantException {
		CandidateAttachmentDto savedDto = null;

		try {
			notNull(dto, "dto must be set");			

			CandidateAttachment entiry = candidateAttachmentMapper.toEntity(dto);
			
			entiry = candidateAttachmentRepository.save(entiry);
			
			savedDto = candidateAttachmentMapper.toDto(entiry);

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
			version = candidateAttachmentRepository.findVersionById(id);
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
			candidateAttachmentRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteCandidateAttachmentsByRequestId(Long id) throws JudicialWarrantException {
		candidateAttachmentRepository.deleteByCandidateRequestId(id);
	}

}

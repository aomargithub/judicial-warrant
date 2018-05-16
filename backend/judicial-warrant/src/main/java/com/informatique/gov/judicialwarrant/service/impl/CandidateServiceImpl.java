package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Candidate;
import com.informatique.gov.judicialwarrant.domain.CandidateHistoryLog;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.CandidateHistoryLogRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.CandidateRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.UserRepository;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.service.CandidateAttachmentService;
import com.informatique.gov.judicialwarrant.service.CandidateService;
import com.informatique.gov.judicialwarrant.service.CandidateStatusService;
import com.informatique.gov.judicialwarrant.service.OrganizationUnitService;
import com.informatique.gov.judicialwarrant.service.SecurityService;
import com.informatique.gov.judicialwarrant.support.dataenum.CandidateStatusEnum;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {
	private CandidateRepository candidateRepository;
	private CandidateStatusService candidateStatusService;
	private CandidateAttachmentService candidateAttachmentService;
	private CandidateHistoryLogRepository candidateHistoryLogRepository;
	private SecurityService securityService;
	private OrganizationUnitService organizationUnitService;
	private UserRepository userRepository;
	private ModelMapper<Candidate, CandidateDto, Long> candidateMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public CandidateDto getById(Long id) throws JudicialWarrantException {
		CandidateDto dto = null;
		try {
			notNull(id, "id must be set");
			
			Candidate entity = candidateRepository.findById(id).get();
			dto = candidateMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public CandidateDto save(CandidateDto candidateDto, CandidateStatusEnum candidateStatusEnum) throws JudicialWarrantException {
		candidateDto.setCurrentStatus(candidateStatusService.getByCode(candidateStatusEnum.getCode()));
		return save(candidateDto);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public CandidateDto save(CandidateDto candidateDto) throws JudicialWarrantException {
		candidateDto.setOrganizationUnit(organizationUnitService.getById(securityService.getUserDetails(securityService.session()).getOrganizationUnit().getId()));
		CandidateHistoryLog candidateHistoryLog = new CandidateHistoryLog();
		Candidate candidate = candidateMapper.toEntity(candidateDto);
		candidate = candidateRepository.save(candidate);
		candidateHistoryLog.setCandidate(candidate);
		candidateHistoryLog.setCreateBy(userRepository.findByLoginNameIgnoreCase(securityService.getPrincipal()));
		candidateHistoryLog.setCreateDate(new Date());
		candidateHistoryLog.setStatus(candidate.getCurrentStatus());
		candidateHistoryLog = candidateHistoryLogRepository.save(candidateHistoryLog);
		return candidateMapper.toDto(candidate);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<CandidateDto> save(Set<CandidateDto> candidateDtos, RequestDto requestDto) throws JudicialWarrantException {
		Set<CandidateDto> candidates = new HashSet<>();
		for(CandidateDto candidateDto : candidateDtos) {
			candidateDto.setRequest(requestDto);
			candidateDto = save(candidateDto);
			candidates.add(candidateDto);
		}
		return candidates;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public CandidateDto update(CandidateDto dto) throws JudicialWarrantException {
		Candidate candidate = candidateMapper.toEntity(dto);
		candidate = candidateRepository.save(candidate);
		return candidateMapper.toDto(candidate);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CandidateDto changeStatus(CandidateDto dto, CandidateStatusEnum candidateStatusEnum) throws JudicialWarrantException {
		dto.setCurrentStatus(candidateStatusService.getByCode(candidateStatusEnum.getCode()));
		dto = update(dto);
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Short getVersionById(Long id) throws JudicialWarrantException {
		return candidateRepository.findVersionById(id);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteCandidatesByRequest(Request request) throws JudicialWarrantException {
		try {
			// delete candidateAttachments to can delete candidate
			candidateAttachmentService.deleteCandidateAttachmentsByRequestId(request.getId());
			// delete all HistoryLog to can delete Candidates
			candidateHistoryLogRepository.deleteByCandidateRequestId(request.getId());
			candidateRepository.deleteCandidateByRequestId(request.getId());
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}


	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Set<CandidateDto> getCandidatesByRequest(Request request) throws JudicialWarrantException {
		Set<CandidateDto> dtos = null;
		try {
			Set<Candidate> candidates = candidateRepository.getCandidatesByRequestId(request.getId());
			dtos = candidateMapper.toDto(candidates);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<CandidateDto> changeStatus(Set<CandidateDto> dtos, CandidateStatusEnum candidateStatusEnum)
			throws JudicialWarrantException {
		Set<CandidateDto> updatedCandidates;
		try {
			updatedCandidates = new HashSet<CandidateDto>(); 
			for(CandidateDto candidateDto : dtos) {
				CandidateDto updatedDto = changeStatus(candidateDto, candidateStatusEnum);
				updatedCandidates.add(updatedDto);
			}
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return updatedCandidates;
	}

}

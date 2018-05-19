package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.ERRequest;
import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.ERRequestRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.JwcdRequestRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestRepository;
import com.informatique.gov.judicialwarrant.rest.dto.CandidateDto;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestDto;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.request.ERRequestRequest;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.ERRequestResponse;
import com.informatique.gov.judicialwarrant.service.CandidateService;
import com.informatique.gov.judicialwarrant.service.ERRequestService;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.service.SecurityService;
import com.informatique.gov.judicialwarrant.support.dataenum.CandidateStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;
import com.informatique.gov.judicialwarrant.support.validator.ErWorkflowValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ERRequestServiceImpl implements ERRequestService {
	private InternalRequestService requestService;
	private ModelMapper<Request, RequestDto, Long> requestMapper;
	private ModelMapper<ERRequest, ERRequestResponse, Long> erRequestMapper;
	private ModelMapper<ERRequest, ERRequestForInternalResponse, Long> erRequestForInternalMapper;
	private ERRequestRepository erRequestRepository;
	private CandidateService candidateService;
	private JwcdRequestRepository jwcdRequestRepository;
	private RequestRepository requestRepository;
	private SecurityService securityService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<ERRequestForInternalResponse> getAll() throws JudicialWarrantException {
		List<ERRequestForInternalResponse> dtos = null;
		try {
			List<ERRequest> entities = erRequestRepository.findAll();
			dtos = erRequestForInternalMapper.toDto(entities);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<ERRequestResponse> getAllByOrganizationUnit() throws JudicialWarrantException {
		List<ERRequestResponse> dtos = null;
		try {
			OrganizationUnitDto organizationUnitDto = securityService.getUserDetails(securityService.session()).getOrganizationUnit();
			List<ERRequest> entities = erRequestRepository.findAllByRequestOrganizationUnitId(organizationUnitDto.getId());
			dtos = erRequestMapper.toDto(entities);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Short getVersionBySerial(String serial) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(serial, "serial must be set");
			version = requestRepository.findVersionBySerial(serial);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ERRequestForInternalResponse getBySerial(String serial) throws JudicialWarrantException {
		ERRequest entity = null;
		ERRequestForInternalResponse dto = null;
		try {
			notNull(serial, "code must be set");
			entity = erRequestRepository.findByRequestSerial(serial);
			dto = erRequestForInternalMapper.toDto(entity);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ERRequestResponse getBySerialAndOrganizationUnit(String serial) throws JudicialWarrantException {
		ERRequest entity = null;
		ERRequestResponse dto = null;
		try {
			notNull(serial, "code must be set");
			OrganizationUnitDto organizationUnitDto = securityService.getUserDetails(securityService.session()).getOrganizationUnit();
			entity = erRequestRepository.findByRequestSerialAndRequestOrganizationUnitId(serial, organizationUnitDto.getId());
			dto = erRequestMapper.toDto(entity);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestResponse createRequest(ERRequestRequest erRequestRequest) throws JudicialWarrantException {
		notNull(erRequestRequest, "data can't be null");
		ERRequestResponse erRequestResponse = null;
		try {
			ERRequest erRequest = new ERRequest();
			Request request = requestService.create(RequestTypeEnum.ER, Strings.EMPTY);
			erRequest.setId(request.getId());
			erRequest.setRequest(request);
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(erRequestRequest.getJwcdRequestDto().getSerial());
			erRequest.setJwcdRequest(jwcdRequest);
			erRequest = erRequestRepository.save(erRequest);
			Set<CandidateDto> candidateDtos = candidateService.save(erRequestRequest.getCandidates(), requestMapper.toDto(erRequest.getRequest()));
			erRequestResponse = erRequestMapper.toDto(erRequest);
			erRequestResponse.setCandidates(candidateDtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestResponse updateRequest(String serial, ERRequestRequest erRequestRequest)
			throws JudicialWarrantException {
		
		ERRequestResponse erRequestResponse = null;
		try {
			notNull(serial, "code must be set");
			OrganizationUnitDto organizationUnitDto = securityService.getUserDetails(securityService.session()).getOrganizationUnit();
			ERRequest erRequest = erRequestRepository.findByRequestSerialAndRequestOrganizationUnitId(serial, organizationUnitDto.getId());
			ErWorkflowValidator.validateForUpdate(erRequest);
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(erRequestRequest.getJwcdRequestDto().getSerial());
			erRequest.setJwcdRequest(jwcdRequest);
			erRequest = erRequestRepository.save(erRequest);
			// delete all previous candidate and insert it again to avoid compare candidate collections
			candidateService.deleteCandidatesByRequest(erRequest.getRequest());
			Set<CandidateDto> candidateDtos = candidateService.save(erRequestRequest.getCandidates(), requestMapper.toDto(erRequest.getRequest()));
			erRequestResponse = erRequestMapper.toDto(erRequest);
			erRequestResponse.setCandidates(candidateDtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestResponse submitRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestResponse erRequestResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			ErWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.RECIEVED);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.RECIEVED, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.SUBMITED);
			erRequestResponse = erRequestMapper.toDto(erRequest);
			erRequestResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse incompleteRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			ErWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.INCOMPLETE);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INCOMPLETE, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.INCOMPLETE);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse rejectRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			ErWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.REJECTED);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.REJECTED, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.REJECTED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse inprogressRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			ErWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.INPROGRESS);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.ACCEPTED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse inTrainingRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			ErWorkflowValidator.validate(erRequest, RequestInternalStatusEnum.INPROGRESS);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.TRAINNING);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse passTrainingRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.PASSED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse failTrainingRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.FAILED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ERRequestForInternalResponse issuedRequest(String serial, ERRequestNotesData erRequestNotesData)
			throws JudicialWarrantException {
		ERRequestForInternalResponse erRequestForInternalResponse = null;
		try {
			ERRequest erRequest = erRequestRepository.findByRequestSerial(serial);
			Request request = requestService.changeStatus(erRequest.getRequest(), RequestInternalStatusEnum.ISSUED, erRequestNotesData.getNotes());
			erRequest.setRequest(request);
			Set<CandidateDto> candidates = candidateService.getCandidatesByRequest(request);
			candidates = candidateService.changeStatus(candidates, CandidateStatusEnum.CARD_RECIEVED);
			erRequestForInternalResponse = erRequestForInternalMapper.toDto(erRequest);
			erRequestForInternalResponse.setCandidates(candidates);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return erRequestForInternalResponse;
	}

}

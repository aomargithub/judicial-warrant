package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.JwcdRequestRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestRepository;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestData;
import com.informatique.gov.judicialwarrant.rest.request.JwcdRequestNotesData;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalDto;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestDto;
import com.informatique.gov.judicialwarrant.service.InternalRequestService;
import com.informatique.gov.judicialwarrant.service.JwcdRequestService;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestTypeEnum;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;
import com.informatique.gov.judicialwarrant.support.validator.JwcdWorkflowValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwcdRequestServiceImpl implements JwcdRequestService {
	private InternalRequestService requestService;
	private ModelMapper<JwcdRequest, JwcdRequestDto, Long> jwcdRequestmapper;
	private ModelMapper<JwcdRequest, JwcdRequestForInternalDto, Long> jobTitleInternalRequestmapper;
	private JwcdRequestRepository jwcdRequestRepository;
	private RequestRepository requestRepository;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<JwcdRequestDto> getAll() throws JudicialWarrantException {
		List<JwcdRequestDto> dtos = null;
		try {
			List<JwcdRequest> entities = jwcdRequestRepository.findAll();
			dtos = jwcdRequestmapper.toDto(entities);
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
			notNull(serial, "id must be set");
			version = requestRepository.findVersionBySerial(serial);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;		
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public JwcdRequestDto getBySerial(String serial) throws JudicialWarrantException {
		JwcdRequest entity = null;
		JwcdRequestDto dto = null;
		try {
			notNull(serial, "code must be set");
			entity = jwcdRequestRepository.findByRequestSerial(serial);
			dto = jwcdRequestmapper.toDto(entity);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestDto createRequest(JwcdRequestData jwcdRequestData) throws JudicialWarrantException {
		
		JwcdRequestDto jwcdRequestDto = null;
		
		try {
			JwcdRequest jwcdRequest = new JwcdRequest();
			Request request = requestService.create(RequestTypeEnum.JWCD);
			jwcdRequest.setJobTitle(jwcdRequestData.getJobTitle());
			jwcdRequest.setRequest(request);
			jwcdRequestRepository.save(jwcdRequest);
			jwcdRequestDto = jwcdRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		} 
		return jwcdRequestDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestDto updateRequest(String serial, JwcdRequestData jobNameRequestData)
			throws JudicialWarrantException {
		JwcdRequestDto jwcdRequestDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validateForUpdate(jwcdRequest);
			jwcdRequest.setJobTitle(jobNameRequestData.getJobTitle());
			jwcdRequest = jwcdRequestRepository.save(jwcdRequest);
			jwcdRequestDto = jwcdRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestDto submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestDto jwcdRequestDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.RECIEVED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.RECIEVED, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestDto = jwcdRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestDto;
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalDto incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestForInternalDto jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.INCOMPLETE);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.INCOMPLETE, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jobTitleInternalRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalDto rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestForInternalDto jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.REJECTED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.REJECTED, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jobTitleInternalRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalDto inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestForInternalDto jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.INPROGRESS);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.INPROGRESS, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jobTitleInternalRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalDto lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestForInternalDto jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jobTitleInternalRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalDto lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestForInternalDto jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jobTitleInternalRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalDto lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestForInternalDto jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REJECTED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REJECTED, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jobTitleInternalRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalDto issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData) throws JudicialWarrantException {
		JwcdRequestForInternalDto jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.ISSUED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.ISSUED, jwcdRequestNotesData);
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jobTitleInternalRequestmapper.toDto(jwcdRequest);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}
	

}

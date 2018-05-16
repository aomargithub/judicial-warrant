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
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestForInternalResponse;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestResponse;
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
	private ModelMapper<JwcdRequest, JwcdRequestResponse, Long> jwcdRequestmapper;
	private ModelMapper<JwcdRequest, JwcdRequestForInternalResponse, Long> jwcdInternalRequestmapper;
	private JwcdRequestRepository jwcdRequestRepository;
	private RequestRepository requestRepository;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<JwcdRequestResponse> getAll() throws JudicialWarrantException {
		List<JwcdRequestResponse> dtos = null;
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
	public JwcdRequestResponse getBySerial(String serial) throws JudicialWarrantException {
		JwcdRequest entity = null;
		JwcdRequestResponse dto = null;
		try {
			notNull(serial, "code must be set");
			entity = jwcdRequestRepository.findByRequestSerial(serial);
			dto = jwcdRequestmapper.toDto(entity);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestResponse createRequest(JwcdRequestData jwcdRequestData) throws JudicialWarrantException {

		JwcdRequestResponse jwcdRequestDto = null;

		try {
			JwcdRequest jwcdRequest = new JwcdRequest();
			Request request = requestService.create(RequestTypeEnum.JWCD, jwcdRequestData.getNotes());
			jwcdRequest.setId(request.getId());
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
	public JwcdRequestResponse updateRequest(String serial, JwcdRequestData jobNameRequestData)
			throws JudicialWarrantException {
		JwcdRequestResponse jwcdRequestDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validateForUpdate(jwcdRequest);
			jwcdRequest.setJobTitle(jobNameRequestData.getJobTitle());
			jwcdRequest = jwcdRequestRepository.save(jwcdRequest);
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
	public JwcdRequestResponse submitRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestResponse jwcdRequestDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.RECIEVED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.RECIEVED,
					jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
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
	public JwcdRequestForInternalResponse incompleteRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestForInternalResponse jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.INCOMPLETE);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(),
					RequestInternalStatusEnum.INCOMPLETE, jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jwcdInternalRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalResponse rejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestForInternalResponse jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.REJECTED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.REJECTED,
					jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jwcdInternalRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalResponse inprogressRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestForInternalResponse jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.INPROGRESS);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(),
					RequestInternalStatusEnum.INPROGRESS, jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jwcdInternalRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalResponse lawAffairsReviewRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestForInternalResponse jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(),
					RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW, jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jwcdInternalRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalResponse lawAffairsAcceptRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestForInternalResponse jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(),
					RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED, jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jwcdInternalRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalResponse lawAffairsRejectRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestForInternalResponse jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REJECTED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(),
					RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REJECTED, jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jwcdInternalRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwcdRequestForInternalResponse issuedRequest(String serial, JwcdRequestNotesData jwcdRequestNotesData)
			throws JudicialWarrantException {
		JwcdRequestForInternalResponse jwcdRequestForInternalDto = null;
		try {
			JwcdRequest jwcdRequest = jwcdRequestRepository.findByRequestSerial(serial);
			JwcdWorkflowValidator.validate(jwcdRequest, RequestInternalStatusEnum.ISSUED);
			Request request = requestService.changeStatus(jwcdRequest.getRequest(), RequestInternalStatusEnum.ISSUED,
					jwcdRequestNotesData.getNotes());
			jwcdRequest.setRequest(request);
			jwcdRequestForInternalDto = jwcdInternalRequestmapper.toDto(jwcdRequest);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return jwcdRequestForInternalDto;
	}

}

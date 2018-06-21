package com.informatique.gov.judicialwarrant.rest.handler.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotModifiedException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledRegistrationHandler;
import com.informatique.gov.judicialwarrant.rest.request.EntitledRegistrationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.service.EntitledAttachmentService;
import com.informatique.gov.judicialwarrant.service.EntitledRegistrationService;
import com.informatique.gov.judicialwarrant.service.EntitledService;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledRegistrationHandlerImpl implements EntitledRegistrationHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntitledRegistrationService entitledRegistrationService;
	private RequestAttachmentService requestAttachmentService;
	private EntitledService entitledService;
	private EntitledAttachmentService entitledAttachmentService;

	@Override
	public ResponseEntity<?> generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response,
			String serial) throws JudicialWarrantException {
		ResponseEntity<?> responseEntity = null;
		try {	
			entitledRegistrationService.generateEntitledRegistrationReportByRequestSerial(response, serial);
			responseEntity = ResponseEntity.ok(response);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<List<EntitledRegistrationDto>> getAll(Authentication authentication) throws JudicialWarrantException {
		
		ResponseEntity<List<EntitledRegistrationDto>> response = null;
		try {
			List<EntitledRegistrationDto> dtos = entitledRegistrationService.getAll(authentication);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}


	@Override
	public ResponseEntity<EntitledRegistrationDto> getBySerial(Authentication authentication, String serial) throws JudicialWarrantException {
		ResponseEntity<EntitledRegistrationDto> response = null;
		try {
			EntitledRegistrationDto dto = entitledRegistrationService.getBySerial(authentication, serial);
			if (dto == null) {
				throw new ResourceNotFoundException(serial);
			}
			response = ResponseEntity.ok().body(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<EntitledRegistrationDto> create(EntitledRegistrationDto entitledRegistrationDto)
			throws JudicialWarrantException {
		ResponseEntity<EntitledRegistrationDto> response = null;
		try {

			EntitledRegistrationDto savedDto = entitledRegistrationService.create(entitledRegistrationDto);

			response = ResponseEntity.status(HttpStatus.CREATED).body(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<EntitledRegistrationDto> update(String serial, EntitledRegistrationDto entitledRegistrationDto, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<EntitledRegistrationDto> response = null;
		try {

			notNull(entitledRegistrationDto, "entitledRegistrationDto must be set");

			if (etag == null) {
				throw new PreConditionRequiredException(serial);
			}

			Short version = entitledRegistrationService.getVersionBySerial(serial);

			if (version == null) {
				throw new ResourceNotFoundException(serial);
			}

			if (!version.equals(etag)) {
				throw new ResourceModifiedException(serial, etag, version);
			}

			EntitledRegistrationDto savedDto = entitledRegistrationService.update(serial, entitledRegistrationDto);

			response = ResponseEntity.ok(savedDto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<EntitledRegistrationDto> submit(String serial, EntitledRegistrationChangeStatusRequest registrationChangeStatusRequest) throws JudicialWarrantException {
		ResponseEntity<EntitledRegistrationDto> response = null;
		try {

			EntitledRegistrationDto dto = entitledRegistrationService.submit(serial, registrationChangeStatusRequest);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<List<RequestAttachmentDto>> getAllRequestAttachmentByRequestSerial(String serial)
			throws JudicialWarrantException {
		ResponseEntity<List<RequestAttachmentDto>> response = null;
		try {
			List<RequestAttachmentDto> dtos = requestAttachmentService.getAllByRequestSerial(serial);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestAttachmentDto> getRequestAttachmentById(String serial, Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			notNull(id, "id must be set");
			RequestAttachmentDto dto = null;
			
			if(etag != null) {
				Short version = requestAttachmentService.getVersionById(serial, id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = requestAttachmentService.getById(serial, id);
			
			if(dto == null) {
				throw new ResourceNotFoundException(id);
			}
			
			response = ResponseEntity.ok().eTag(dto.getVersion().toString()).body(dto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return response;
	}

	@Override
	public ResponseEntity<RequestAttachmentDto> updateRequestAttachment(String serial, RequestAttachmentDto dto, Long id, Short etag)
			throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			notNull(dto, "candidateAttachmentDto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = requestAttachmentService.getVersionById(serial, id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			dto.setId(id);
			dto.setVersion(etag);
			
			RequestAttachmentDto savedDto = requestAttachmentService.update(serial, dto,id);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<RequestAttachmentDto> createRequestAttachment(String serial, RequestAttachmentDto dto, MultipartFile file) throws JudicialWarrantException {
		ResponseEntity<RequestAttachmentDto> response = null;
		try {
			
			RequestAttachmentDto savedDto = requestAttachmentService.create(serial, dto, file);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;	
	}

	@Override
	public ResponseEntity<Void> deleteRequestAttachment(String serial, Long id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			requestAttachmentService.delete(serial, id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<EntitledDto> getEntitledById(Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledDto> response = null;
		try {
			notNull(id, "id must be set");
			EntitledDto dto = null;
			
			if(etag != null) {
				Short version = entitledService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = entitledService.getById(id);
			
			if(dto == null) {
				throw new ResourceNotFoundException(id);
			}
			
			response = ResponseEntity.ok().eTag(dto.getVersion().toString()).body(dto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return response;
	}
	
	@Override
	public ResponseEntity<EntitledDto> saveEntitled(EntitledDto entitledDto)
			throws JudicialWarrantException {
		ResponseEntity<EntitledDto> response = null;
		try {
			
			EntitledDto savedDto = entitledService.save(entitledDto);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;		
	}
	
	@Override
	public ResponseEntity<EntitledDto> updateEntitled(EntitledDto entitledDto, Long id,
			Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledDto> response = null;
		try {
			notNull(entitledDto, "entitledDto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = entitledService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			entitledDto.setId(id);
			entitledDto.setVersion(etag);
			
			EntitledDto savedDto = entitledService.update(entitledDto);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	
	
	@Override
	public ResponseEntity<Void> deleteEntitled(Long id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			entitledService.deleteById(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<List<EntitledDto>> getAllEntitledsByEntitledRegistrationSerial(String serial)
			throws JudicialWarrantException {
		ResponseEntity<List<EntitledDto>> response = null;
		try {
			List<EntitledDto> dtos = entitledService.getAllByEntitledRegistrationSerial(serial);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}
	
	@Override
	public ResponseEntity<EntitledAttachmentDto> getEntitledAttachmentById(Long id, Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledAttachmentDto> response = null;
		try {
			notNull(id, "id must be set");
			EntitledAttachmentDto dto = null;
			
			if(etag != null) {
				Short version = entitledAttachmentService.getVersionById(id);
				
				if(version == null) {
					throw new ResourceNotFoundException(id);
				}
				
				if(version.equals(etag)) {
					throw new ResourceNotModifiedException(id, version);
				}
			}
			
			dto = entitledAttachmentService.getById(id);
			
			if(dto == null) {
				throw new ResourceNotFoundException(id);
			}
			
			response = ResponseEntity.ok().eTag(dto.getVersion().toString()).body(dto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return response;
	}
	
	@Override
	public ResponseEntity<EntitledAttachmentDto> saveEntitledAttachment(EntitledAttachmentDto dto, MultipartFile file)
			throws JudicialWarrantException {
		ResponseEntity<EntitledAttachmentDto> response = null;
		try {
			
			EntitledAttachmentDto savedDto = entitledAttachmentService.save(dto, file);
			
			response = ResponseEntity.ok(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;		
	}
	
	@Override
	public ResponseEntity<EntitledAttachmentDto> updateEntitledAttachment(EntitledAttachmentDto dto, Long id,
			Short etag) throws JudicialWarrantException {
		ResponseEntity<EntitledAttachmentDto> response = null;
		try {
			notNull(dto, "dto must be set");
			notNull(id, "id must be set");
			
			if(etag == null) {
				throw new PreConditionRequiredException(id);
			}
			
			Short version = entitledAttachmentService.getVersionById(id);
			
			if(version == null) {
				throw new ResourceNotFoundException(id);
			}
			
			if(!version.equals(etag)) {
				throw new ResourceModifiedException(id, etag, version);
			}
			
			dto.setId(id);
			dto.setVersion(etag);
			
			EntitledAttachmentDto savedDto = entitledAttachmentService.update(dto);
			
			response = ResponseEntity.ok().body(savedDto);
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}

	@Override
	public ResponseEntity<List<EntitledAttachmentDto>> getAllEntitledAttachmentByEntitledId(Long id)
			throws JudicialWarrantException {
		ResponseEntity<List<EntitledAttachmentDto>> response = null;
		try {
			List<EntitledAttachmentDto> dtos = entitledAttachmentService.getByEntitledId(id);
			response = ResponseEntity.ok(dtos);
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;		
	}
	
	@Override
	public ResponseEntity<Void> deleteEntitledAttachment(Long id) throws JudicialWarrantException {
		ResponseEntity<Void> response = null;
		try {
			
			entitledAttachmentService.delete(id);
			
			response = ResponseEntity.ok().build();
			
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}return response;
	}
	
	/*@Override
	public ResponseEntity<ERRequestForInternalResponse> incompleteRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.incompleteRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> rejectRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.rejectRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestForInternalResponse> inprogressRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.inprogressRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> inTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.inTrainingRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> passTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.passTrainingRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<ERRequestForInternalResponse> failTrainingRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.failTrainingRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}

	@Override
	public ResponseEntity<ERRequestForInternalResponse> issuedRequest(String serial, ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		ResponseEntity<ERRequestForInternalResponse> response = null;
		try {

			ERRequestForInternalResponse dto = requestService.issuedRequest(serial, erRequestNotesData);

			response = ResponseEntity.ok(dto);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return response;
	}*/

}

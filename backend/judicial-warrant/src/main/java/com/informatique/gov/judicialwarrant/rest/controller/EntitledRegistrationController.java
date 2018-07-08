package com.informatique.gov.judicialwarrant.rest.controller;



import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledRegistrationDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.rest.handler.EntitledRegistrationHandler;
import com.informatique.gov.judicialwarrant.rest.request.EntitledChangeStatusRequest;
import com.informatique.gov.judicialwarrant.rest.request.EntitledRegistrationChangeStatusRequest;
import com.informatique.gov.judicialwarrant.support.validator.EntitledRegistrationDtoValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entitledRegistrations")
public class EntitledRegistrationController {

	private EntitledRegistrationHandler entitledRegistrationHandlerHandler;
	private EntitledRegistrationDtoValidator entitledRegistrationDtoValidator;
	
	@InitBinder("entitledRegistrationDto")
	protected void InitEntitledRegistrationDtoBinder(WebDataBinder binder) {
		binder.addValidators(entitledRegistrationDtoValidator);
	}
	
	@GetMapping("/serial={serial}/entitledReport")
	public ResponseEntity<?> generateEntitledRegistrationReportByRequestSerial(HttpServletResponse response,
			@PathVariable String serial) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.generateEntitledRegistrationReportByRequestSerial(response, serial);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(Authentication authentication) throws JudicialWarrantException {
		return  entitledRegistrationHandlerHandler.getAll(authentication);
	}

	@GetMapping("/serial={serial}")
	public ResponseEntity<?> getBySerial(@PathVariable String serial, Authentication authentication) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getBySerial(authentication, serial);		
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> create(@Valid @RequestBody EntitledRegistrationDto entitledRegistrationDto) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.create(entitledRegistrationDto);
	}
	
	@PutMapping(path = "/serial={serial}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> update(@PathVariable String serial, @Valid @RequestBody EntitledRegistrationDto entitledRegistrationDto,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.update(serial, entitledRegistrationDto, eTag);
	}
	
	@PutMapping(path = "/serial={serial}/submission")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> submitRequest(@PathVariable String serial, @RequestBody EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.submit(serial, entitledRegistrationChangeStatusRequest);
	}
	
	@PutMapping(path = "/serial={serial}/inProgress")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> inProgressRequest(@PathVariable String serial, @RequestBody EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.inProgress(serial, entitledRegistrationChangeStatusRequest);
	}

	@PutMapping(path = "/serial={serial}/inCompletion")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> incompleteRequest(@PathVariable String serial, @RequestBody EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.inComplete(serial, entitledRegistrationChangeStatusRequest);
	}
	
	@PutMapping(path = "/serial={serial}/rejection")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> rejectRequest(@PathVariable String serial, @RequestBody EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.reject(serial, entitledRegistrationChangeStatusRequest);
	}
	
	@GetMapping(path = "/serial={serial}/requestAttachments/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OFFICER')")
	public ResponseEntity<?> getRequestAttachmentById(@PathVariable String serial, @PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getRequestAttachmentById(serial, id, eTag);
	}
	
	@GetMapping("/serial={serial}/requestAttachments/{id}/ucmDocumentId={ucmDocumentId}/download")
	public ResponseEntity<byte[]> downloadRequestAttachment(
			@PathVariable String serial,  @PathVariable Long id,  @PathVariable String ucmDocumentId) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.downloadRequestAttachmentFile(serial, id, ucmDocumentId);
	}
	
	@GetMapping(path = "/serial={serial}/requestAttachments")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OFFICER')")
	public ResponseEntity<?> getAllRequestAttachmentByRequestSerial(@PathVariable String serial,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getAllRequestAttachmentByRequestSerial(serial);
	}

	@PostMapping(path = "/serial={serial}/requestAttachments")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createRequestAttachment(@PathVariable String serial, @Valid @RequestPart("requestAttachment") RequestAttachmentDto RequestAttachment, @RequestPart("file") MultipartFile file) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.createRequestAttachment(serial, RequestAttachment, file);
	}

	@PutMapping("/serial={serial}/requestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> updateRequestAttachment(@PathVariable String serial, @Valid @RequestBody RequestAttachmentDto dto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.updateRequestAttachment(serial, dto, id, eTag);
	}

	@DeleteMapping("/serial={serial}/requestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> deleteRequestAttachment(@PathVariable String serial, @PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.deleteRequestAttachment(serial, id);
	}
	
	@GetMapping(path = "/serial={serial}/entitleds")
	public ResponseEntity<?> getAllEntitleds(@PathVariable("serial") String entiteldRegistrationSerial) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getAllEntitledsByEntitledRegistrationSerial(entiteldRegistrationSerial);
	}

	@GetMapping(path = "/serial={serial}/entitleds/{id}")
	public ResponseEntity<?> getEntitledById(@PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getEntitledById(id, eTag);
	}
	
	@GetMapping("/serial={serial}/entitledAttachments/{id}/ucmDocumentId={ucmDocumentId}/download")
	public ResponseEntity<byte[]> downloadEntitledAttachment(
			@PathVariable String serial,  @PathVariable Long id,  @PathVariable String ucmDocumentId) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.downloadEntitledAttachmentFile(serial, id, ucmDocumentId);
	}

	@PostMapping(path = "/serial={serial}/entitleds")
	public ResponseEntity<?> saveEntitled(@Valid @RequestBody EntitledDto entitledDto) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.saveEntitled(entitledDto);
	}

	@PutMapping("/entitleds/{id}")
	public ResponseEntity<?> updateEntitled(@Valid @RequestBody EntitledDto entitledDto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.updateEntitled(entitledDto, id, eTag);
	}

	@DeleteMapping("/serial={serial}/entitleds/{id}")
	public ResponseEntity<?> deleteEntitled(@PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.deleteEntitled(id);
	}
	
	@GetMapping(path = "/serial={serial}/entitleds/{id}/entitledAttachments")
	public ResponseEntity<?> getAllEntitledAttachments(@PathVariable("id") Long entiteldId) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getAllEntitledAttachmentByEntitledId(entiteldId);
	}

	@GetMapping(path = "/serial={serial}/entitleds/entitledAttachments/{id}")
	public ResponseEntity<?> getEntitledAttachmentById(@PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getEntitledAttachmentById(id, eTag);
	}

	@PostMapping(path = "/serial={serial}/entitleds/{id}/entitledAttachments")
	public ResponseEntity<?> saveEntitledAttachment(@RequestPart("entitledAttachment") EntitledAttachmentDto dto, @RequestPart("file") MultipartFile file) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.saveEntitledAttachment(dto, file);
	}

	@PutMapping("/serial={serial}/entitleds/entitledAttachments/{id}")
	public ResponseEntity<?> updateEntitledAttachment(@RequestBody EntitledAttachmentDto dto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.updateEntitledAttachment(dto, id, eTag);
	}

	
	@DeleteMapping("/serial={serial}/entitleds/entitledAttachments/{id}")
	public ResponseEntity<?> deleteEntitledAttachment(@PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.deleteEntitledAttachment(id);
	}
	
	@PutMapping("/serial={serial}/entitleds/{id}/acception")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> acceptEntitled(@PathVariable String serial, @RequestBody EntitledChangeStatusRequest changeStatusRequest, @PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.acceptEntitled(serial, id, changeStatusRequest.getNote());
	}
	
	@PutMapping("/serial={serial}/entitleds/acception")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> acceptAllEntitleds(@PathVariable String serial, @RequestBody EntitledChangeStatusRequest changeStatusRequest) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.acceptAllEntitleds(serial, changeStatusRequest.getNote());
	}
	
	@PutMapping("/serial={serial}/entitleds/{id}/rejection")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> rejectEntitled(@PathVariable String serial, @RequestBody EntitledChangeStatusRequest changeStatusRequest, @PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.rejectEntitled(serial, id, changeStatusRequest.getNote());
	}
	
	@PutMapping("/serial={serial}/entitleds/{id}/inTraining")
	@PreAuthorize("hasRole('ROLE_TRAINING_INSTITUTE')")
	public ResponseEntity<?> inTrainingEntitled(@PathVariable String serial, @RequestBody EntitledChangeStatusRequest changeStatusRequest, @PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.inTrainingEntitled(serial, id, changeStatusRequest.getNote());
	}
	
	@PutMapping("/serial={serial}/entitleds/{id}/passed")
	@PreAuthorize("hasRole('ROLE_TRAINING_INSTITUTE')")
	public ResponseEntity<?> passEntitled(@PathVariable String serial, @RequestBody EntitledChangeStatusRequest changeStatusRequest, @PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.passedEntitled(serial, id, changeStatusRequest.getNote());
	}
	
	@PutMapping("/serial={serial}/entitleds/{id}/failture")
	@PreAuthorize("hasRole('ROLE_TRAINING_INSTITUTE')")
	public ResponseEntity<?> failEntitled(@PathVariable String serial, @RequestBody EntitledChangeStatusRequest changeStatusRequest, @PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.failEntitled(serial, id, changeStatusRequest.getNote());
	}
	
	@PutMapping("/serial={serial}/entitleds/{id}/cardRecieved")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> cardRecievedEntitled(@PathVariable String serial, @RequestBody EntitledChangeStatusRequest changeStatusRequest, @PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.cardRecievedEntitled(serial, id, changeStatusRequest.getNote());
	}
	
}

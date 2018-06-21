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
	
	@GetMapping("/serial={serial}/entitledReport/")
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
	public ResponseEntity<?> update(@PathVariable String serial, @RequestBody EntitledRegistrationDto entitledRegistrationDto,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.update(serial, entitledRegistrationDto, eTag);
	}
	
	@GetMapping(path = "/serial={serial}/RequestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> getRequestAttachmentById(@PathVariable String serial, @PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getRequestAttachmentById(serial, id, eTag);
	}
	
	@GetMapping(path = "/serial={serial}/RequestAttachments")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> getAllRequestAttachmentByRequestSerial(@PathVariable String serial,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getAllRequestAttachmentByRequestSerial(serial);
	}

	@PostMapping(path = "/serial={serial}/RequestAttachments")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> createRequestAttachment(@PathVariable String serial, @Valid @RequestPart("dto") RequestAttachmentDto dto, @RequestPart("file") MultipartFile file) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.createRequestAttachment(serial, dto, file);
	}

	@PutMapping("/serial={serial}/RequestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
	public ResponseEntity<?> updateRequestAttachment(@PathVariable String serial, @Valid @RequestBody RequestAttachmentDto dto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.updateRequestAttachment(serial, dto, id, eTag);
	}

	@DeleteMapping("/serial={serial}/RequestAttachments/{id}")
	@PreAuthorize("hasRole('ROLE_OFFICER')")
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

	@PostMapping(path = "/serial={serial}/entitleds")
	public ResponseEntity<?> saveEntitled(@Valid @RequestBody EntitledDto entitledDto) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.saveEntitled(entitledDto);
	}

	@PutMapping("/entitleds/{id}")
	public ResponseEntity<?> updateEntitled(@Valid @RequestBody EntitledDto entitledDto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.updateEntitled(entitledDto, id, eTag);
	}

	@DeleteMapping("entitleds/{id}")
	public ResponseEntity<?> deleteEntitled(@PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.deleteEntitled(id);
	}
	
	@GetMapping(path = "/entitleds/entiteldId={entiteldId}/entitledAttachments")
	public ResponseEntity<?> getAllEntitledAttachments(@PathVariable("entiteldId") Long entiteldId) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getAllEntitledAttachmentByEntitledId(entiteldId);
	}

	@GetMapping(path = "/entitleds/entitledAttachments/{id}")
	public ResponseEntity<?> getEntitledAttachmentById(@PathVariable Long id,
			@RequestHeader(name = "If-None-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.getEntitledAttachmentById(id, eTag);
	}

	@PostMapping(path = "/entitleds/entitledAttachments")
	public ResponseEntity<?> saveEntitledAttachment(@RequestPart("dto") EntitledAttachmentDto dto, @RequestPart("file") MultipartFile file) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.saveEntitledAttachment(dto, file);
	}

	@PutMapping("/entitleds/entitledAttachments/{id}")
	public ResponseEntity<?> updateEntitledAttachment(@RequestBody EntitledAttachmentDto dto, @PathVariable Long id,
			@RequestHeader(name = "If-Match", required = false) Short eTag) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.updateEntitledAttachment(dto, id, eTag);
	}

	
	@DeleteMapping("/entitleds/entitledAttachments/{id}")
	public ResponseEntity<?> deleteEntitledAttachment(@PathVariable Long id) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.deleteEntitledAttachment(id);
	}
	
	@PutMapping(path = "/serial={serial}/submission")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> submitRequest(@PathVariable String serial, @RequestBody EntitledRegistrationChangeStatusRequest entitledRegistrationChangeStatusRequest) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.submit(serial, entitledRegistrationChangeStatusRequest);
	}

	/*@PutMapping(path = "/serial={serial}/incompleteRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> incompleteRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.incompleteRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/rejectRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> rejectRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.rejectRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inprogressRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> inprogressRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.inprogressRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/inTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> inTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.inTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/passTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> passTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.passTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/failTrainingRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> failTrainingRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.failTrainingRequest(serial, erRequestNotesData);
	}
	
	@PutMapping(path = "/serial={serial}/issuedRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> issuedRequest(@PathVariable String serial, @RequestBody ERRequestNotesData erRequestNotesData) throws JudicialWarrantException {
		return entitledRegistrationHandlerHandler.issuedRequest(serial, erRequestNotesData);
	}*/
	
}

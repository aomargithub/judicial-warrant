package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.domain.AttachmentType;
import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestAttachment;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.AttachmentTypeRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestAttachmentRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestRepository;
import com.informatique.gov.judicialwarrant.rest.dto.RequestAttachmentDto;
import com.informatique.gov.judicialwarrant.service.RequestAttachmentService;
import com.informatique.gov.judicialwarrant.support.integration.contentmanger.ContentManager;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestAttachmentServiceImpl implements RequestAttachmentService {

	private RequestAttachmentRepository attachmentRepository;
	private AttachmentTypeRepository attachmentTypeRepository;
	private RequestRepository requestRepository;
	private ContentManager contentManager;
	private ModelMapper<RequestAttachment, RequestAttachmentDto, Long> requestAttachmentMapper;

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=true)
	public List<RequestAttachmentDto> getAllByRequestSerial(String serial) throws JudicialWarrantException {
		List<RequestAttachmentDto> dtos = null;
		try {
		
			List<RequestAttachment> requestAttachments=attachmentRepository.findByRequestSerial(serial);
			dtos=requestAttachmentMapper.toDto(requestAttachments);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=true)
	public List<RequestAttachmentDto> getAll() throws JudicialWarrantException {
		List<RequestAttachmentDto> dtos = null;
		try {
		
			List<RequestAttachment> requestAttachments=attachmentRepository.findAll();
			dtos=requestAttachmentMapper.toDto(requestAttachments);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public RequestAttachmentDto create(String serial, RequestAttachmentDto requestAttachmentDto, MultipartFile file) throws JudicialWarrantException {
		RequestAttachmentDto savedDto = null;

		try {
			notNull(requestAttachmentDto, "dto must be set");

			RequestAttachment entiry = requestAttachmentMapper.toNewEntity(requestAttachmentDto);						
			AttachmentType attachmentType=attachmentTypeRepository.findById(requestAttachmentDto.getAttachmentType().getId()).get();
			Request request=requestRepository.findBySerial(serial);
			
			Map<String, String> properties = new HashMap<String, String>();
//			properties.put("dCollectionName", request.getSerial());
			String ucmId = contentManager.checkin(properties, file);			
			
			entiry.setAttachmentType(attachmentType);
			entiry.setRequest(request);
			entiry.setUcmDocumentId(ucmId);
			entiry = attachmentRepository.save(entiry);
			
			savedDto = requestAttachmentMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;	
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public RequestAttachmentDto update(String serial, RequestAttachmentDto requestAttachmentDto, Long id)
			throws JudicialWarrantException {
		
		RequestAttachmentDto savedDto = null;

		try {
			notNull(requestAttachmentDto, "dto must be set");			

			RequestAttachment entiry = requestAttachmentMapper.toEntity(requestAttachmentDto);

			entiry = attachmentRepository.save(entiry);
			
			savedDto = requestAttachmentMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(String serial, Long id) throws JudicialWarrantInternalException {
		try {
			notNull(id, "id must be set");
			attachmentRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=true)
	public RequestAttachmentDto getById(String serial, Long id) throws JudicialWarrantException {
		RequestAttachmentDto dto = null;
		try {
			notNull(id, "id must be set");
			
			RequestAttachment entity = attachmentRepository.findById(id).get();
			dto = requestAttachmentMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Short getVersionById(String serial, Long id) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(id, "id must be set");
			version = attachmentRepository.findVersionById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;		
	}


}

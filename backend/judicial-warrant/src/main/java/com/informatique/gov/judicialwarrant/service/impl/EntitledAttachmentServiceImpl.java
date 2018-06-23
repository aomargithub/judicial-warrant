package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledAttachment;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.AttachmentTypeRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledAttachmentRepository;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledAttachmentDto;
import com.informatique.gov.judicialwarrant.service.EntitledAttachmentService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledAttachmentService;
import com.informatique.gov.judicialwarrant.support.integration.contentmanger.ContentManager;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EntitledAttachmentServiceImpl implements EntitledAttachmentService, InternalEntitledAttachmentService {
	
	private EntitledAttachmentRepository entitledAttachmentRepository;
	private AttachmentTypeRepository attachmentTypeRepository;
	private ModelMapper<EntitledAttachment, EntitledAttachmentDto, Long> entitledAttachmentMapper;
	private ContentManager contentManager;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Short getVersionById(Long id) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(id, "id must be set");
			version = entitledAttachmentRepository.findVersionById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;		
	}

	@Override
	public EntitledAttachmentDto save(EntitledAttachmentDto dto, MultipartFile file) throws JudicialWarrantException {
		EntitledAttachmentDto savedDto = null;
		try {
			notNull(dto, "entitledDto must be set");
			EntitledAttachment entitledAttachment = entitledAttachmentMapper.toNewEntity(dto);
			entitledAttachment.setFileName(file.getOriginalFilename());
			
			Map<String, String> properties = new HashMap<String, String>();
//			properties.put("dCollectionName", request.getSerial());
			String ucmId = contentManager.checkin(properties, file);
			
			entitledAttachment.setUcmDocumentId(ucmId);
			entitledAttachment = entitledAttachmentRepository.save(entitledAttachment);
			savedDto = entitledAttachmentMapper.toDto(entitledAttachment);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedDto;
	}

	@Override
	public EntitledAttachmentDto update(EntitledAttachmentDto dto) throws JudicialWarrantException {
		EntitledAttachmentDto updatedDto = null;
		try {
			notNull(dto, "entitledDto must be set");
			EntitledAttachment entitled = entitledAttachmentMapper.toNewEntity(dto);
			entitled = entitledAttachmentRepository.save(entitled);
			updatedDto = entitledAttachmentMapper.toDto(entitled);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return updatedDto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<EntitledAttachment> save(Set<EntitledAttachment> entities, Entitled entitled) throws JudicialWarrantException {
		Set<EntitledAttachment> entitledAttachments = null;
		try {
			notNull(entities, "entities must be set");
			notNull(entitled, "entitled must be set");
			notNull(entitled.getId(), "entitled.id must be set");
			
			entitledAttachmentRepository.deleteByEntitledId(entitled.getId());
			
			entitledAttachments = create(entities, entitled);
		}catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return entitledAttachments;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<EntitledAttachment> create(Set<EntitledAttachment> entitieAttachments, Entitled entitled) throws JudicialWarrantException {
		Set<EntitledAttachment> entitledAttachments = null;
		try {
			notNull(entitieAttachments, "entitieAttachments must be set");
			notNull(entitled, "entitled must be set");
			notNull(entitled.getId(), "entitled.id must be set");
			
			entitledAttachments = new HashSet<>();
			
			for(EntitledAttachment entity : entitieAttachments) {
				entity.setEntitled(entitled);
				entity.setAttachmentType(attachmentTypeRepository.findById(entity.getAttachmentType().getId()).get());
				entitledAttachments.add(entitledAttachmentRepository.save(entity));
			}
			
		}catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return entitledAttachments;
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<EntitledAttachmentDto> getByEntitledId(Long id) throws JudicialWarrantException {
		List<EntitledAttachmentDto> entitledAttachmentDtos = null;
		try {
			notNull(id, "id must be set");
			List<EntitledAttachment> entitledAttachments = entitledAttachmentRepository.findByEntitledId(id);
			entitledAttachmentDtos = entitledAttachmentMapper.toDto(entitledAttachments);
		}
		catch(Exception e) {
			throw new JudicialWarrantInternalException(e);
		}	
		return entitledAttachmentDtos;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledAttachmentDto getById(Long id) throws JudicialWarrantException {
		EntitledAttachmentDto dto = null;
		try {
			notNull(id, "id must be set");
			EntitledAttachment entitledAttachment = entitledAttachmentRepository.findById(id).get();
			dto = entitledAttachmentMapper.toDto(entitledAttachment);
		}
		catch(Exception e) {
			throw new JudicialWarrantInternalException(e);
		}	
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) throws JudicialWarrantException {
		try {
			entitledAttachmentRepository.deleteById(id);
		}
		catch(Exception e) {
			throw new JudicialWarrantInternalException(e);
		}	
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByEntitledId(Long id) throws JudicialWarrantException {
		try {
			entitledAttachmentRepository.deleteByEntitledId(id);
		}
		catch(Exception e) {
			throw new JudicialWarrantInternalException(e);
		}	
	}

}

package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledAttachment;
import com.informatique.gov.judicialwarrant.domain.EntitledRegistration;
import com.informatique.gov.judicialwarrant.domain.EntitledStatus;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.SingleResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.SingleResourceNotFoundException;
import com.informatique.gov.judicialwarrant.exception.SingleResourceVersionNotProvidedException;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledAttachmentRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledHistoryLogRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledStatusRepository;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.service.EntitledService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledAttachmentService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledHistoryLogService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledService;
import com.informatique.gov.judicialwarrant.service.InternalOrganizationUnitService;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EntitledServiceImpl implements EntitledService, InternalEntitledService {
	
	private InternalOrganizationUnitService organizationUnitService;
	private InternalEntitledHistoryLogService entitledHistoryLogService;
	private InternalEntitledAttachmentService entitledAttachmentService;
	private EntitledRepository entitledRepository;
	private EntitledStatusRepository entitledStatusRepository;
	private EntitledHistoryLogRepository entitledHistoryLogRepository;
	private EntitledAttachmentRepository entitledAttachmentRepository;
	private ModelMapper<Entitled, EntitledDto, Long> entitledMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public EntitledDto getById(Long id) throws JudicialWarrantException {
		EntitledDto dto = null;
		try {
			notNull(id, "id must be set");
			
			Entitled entity = entitledRepository.findById(id).get();
			dto = entitledMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<EntitledDto> getAllByEntitledRegistrationSerial(String serial) throws JudicialWarrantException {
		List<EntitledDto> entitledDtos = null;
		try {	
			notNull(serial, "serial must be set");
			List<Entitled> entitleds = entitledRepository.findByEntitledRegistrationRequestSerial(serial);
			entitledDtos = entitledMapper.toDto(entitleds);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return entitledDtos;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<Entitled> save(Set<Entitled> entities, EntitledRegistration entitledRegistration) throws JudicialWarrantException {
		Set<Entitled> savedEntities = null;
		try {
			notNull(entities, "entities must be set");
			notNull(entitledRegistration, "entitledRegistration must be set");
			savedEntities = new HashSet<>();
			for(Entitled entity : entities) {
				if(entity.getId() == null) {
					savedEntities.add(create(entity, entitledRegistration));
				}else {
					savedEntities.add(update(entity));
				}
				
			}
			
		}catch(JudicialWarrantException e) {
			throw e;
		}catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntities;
	}
	
	
	private Entitled create(Entitled entity, EntitledRegistration entitledRegistration) throws JudicialWarrantException {
		Entitled savedEntity = null;
		try {
			notNull(entity, "entity must be set");
			notNull(entitledRegistration, "entitledRegistration must be set");
			
			
			entity.setOrganizationUnit(organizationUnitService.getByCurrentUser());
			entity.setCurrentStatus(entitledStatusRepository.findByCode(EntitledStatusEnum.DRAFT.getCode()));
			entity.setEntitledRegistration(entitledRegistration);
			savedEntity = entitledRepository.save(entity);
			
			Set<EntitledAttachment> attachments = entitledAttachmentService.create(savedEntity.getAttachments(), savedEntity);
			savedEntity.setAttachments(attachments);
			
			entitledHistoryLogService.create(entity);
			
		}catch(JudicialWarrantException e) {
			throw e;
		}catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
		 
		 return savedEntity;
	}
	
	private Entitled update(Entitled entity) throws JudicialWarrantException {
		Entitled savedEntity = null;
		try {
			notNull(entity, "dto must be set");
			notNull(entity.getId(), "dto.id must be set");
			
			validateUpdateEligiablity(entity);
			
			savedEntity = entitledRepository.save(entity);
			
			Set<EntitledAttachment> attachments = entitledAttachmentService.save(savedEntity.getAttachments(), savedEntity);
			savedEntity.setAttachments(attachments);
			
		}catch(JudicialWarrantException e) {
			throw e;
		}catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntity;
	}
	
	private void validateUpdateEligiablity(Entitled entity) throws JudicialWarrantException{
		
		String entitledClassName= Entitled.class.getName();
		
		if(entity.getVersion() == null) {
			throw new SingleResourceVersionNotProvidedException(entitledClassName, entity.getId());
		}
		
		Short version = entitledRepository.findVersionById(entity.getId());
		
		if (version == null) {
			throw new SingleResourceNotFoundException(entitledClassName, entity.getId());
		}
		
		if(!version.equals(entity.getVersion())) {
			throw new SingleResourceModifiedException(entitledClassName, entity.getId(), entity.getVersion(), version);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer deleteByEntitledRegistrationId(Long entitledRegistrationId) throws JudicialWarrantException {
		Integer numberOfDeletedEntities = null;
		try {
			notNull(entitledRegistrationId, "entitledRegistrationId must be set");
			
			entitledHistoryLogRepository.deleteByEntitledEntitledRegistrationId(entitledRegistrationId);
			entitledAttachmentRepository.deleteByEntitledEntitledRegistrationId(entitledRegistrationId);
			numberOfDeletedEntities = entitledRepository.deleteByEntitledRegistrationId(entitledRegistrationId);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		
		return numberOfDeletedEntities;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Long id) throws JudicialWarrantException {
		
		try {
			notNull(id, "id must be set");
			
			entitledHistoryLogRepository.deleteByEntitledId(id);
			entitledAttachmentRepository.deleteByEntitledId(id);
			entitledRepository.deleteById(id);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<EntitledDto> changeStatus(Set<EntitledDto> dtos, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException {
		return entitledMapper.toDto(changeStatusForInternal(entitledMapper.toEntity(dtos), entitledStatusEnum, note));
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<Entitled> changeStatusForInternal(Set<Entitled> entities, EntitledStatusEnum entitledStatusEnum, String note) throws JudicialWarrantException {
		Set<Entitled> savedEntities = null;
		try {
			notNull(entities, "dto must be set");
			notNull(entitledStatusEnum, "entitledStatusEnum must be set");
			
			savedEntities = new HashSet<>();
			
			for(Entitled entity : entities) {
				savedEntities.add(changeStatus(entity, entitledStatusEnum, note));
			}
			
		}catch(JudicialWarrantException e) {
			throw e;
		}catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntities;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto changeStatus(EntitledDto dto, EntitledStatusEnum entitledStatusEnum, String note) throws JudicialWarrantException {
		
		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto),entitledStatusEnum, note));
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Entitled changeStatus(Entitled entity, EntitledStatusEnum entitledStatusEnum, String note) throws JudicialWarrantException {
		
		try {
			notNull(entity, "dto must be set");
			notNull(entity.getId(), "dto.id must be set");
			notNull(entitledStatusEnum, "entitledStatusEnum must be set");
			
			validateUpdateEligiablity(entity);
			
			EntitledStatus entitledStatus = entitledStatusRepository.findByCode(entitledStatusEnum.getCode());
			entity = entitledRepository.getOne(entity.getId());
			
			entity.setCurrentStatus(entitledStatus);
			
			entity = entitledRepository.save(entity);
			
			entitledHistoryLogService.create(entity, note);
			
	
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return entity;
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<Entitled> changeStatusByEntitledRegistrationId(Long entitledRegistrationId, EntitledStatusEnum entitledStatusEnum, String note) throws JudicialWarrantException {
		Set<Entitled> entities = null;
		try {
			notNull(entitledRegistrationId, "dto must be set");
			notNull(entitledStatusEnum, "entitledStatusEnum must be set");
			
			
			entities = entitledRepository.findByEntitledRegistrationId(entitledRegistrationId);
			entities = changeStatusForInternal(entities, entitledStatusEnum, note);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return entities;
	}

}

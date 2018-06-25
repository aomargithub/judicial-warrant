package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledStatus;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledAttachmentRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledHistoryLogRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledRepository;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledStatusRepository;
import com.informatique.gov.judicialwarrant.rest.dto.EntitledDto;
import com.informatique.gov.judicialwarrant.service.EntitledService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledHistoryLogService;
import com.informatique.gov.judicialwarrant.service.InternalEntitledService;
import com.informatique.gov.judicialwarrant.service.InternalOrganizationUnitService;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;
import com.informatique.gov.judicialwarrant.support.integration.contentmanger.ContentManager;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;
import com.informatique.gov.judicialwarrant.support.validator.EntitledWorkflowValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntitledServiceImpl implements EntitledService, InternalEntitledService {

	private InternalOrganizationUnitService organizationUnitService;
	private InternalEntitledHistoryLogService entitledHistoryLogService;
	private EntitledRepository entitledRepository;
	private EntitledStatusRepository entitledStatusRepository;
	private EntitledHistoryLogRepository entitledHistoryLogRepository;
	private EntitledAttachmentRepository entitledAttachmentRepository;
	private ContentManager contentManager;
	private ModelMapper<Entitled, EntitledDto, Long> entitledMapper;
	private EntitledWorkflowValidator entitledWorkflowValidator;

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
	public Short getVersionById(Long id) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(id, "id must be set");
			version = entitledRepository.findVersionById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto save(EntitledDto entitledDto) throws JudicialWarrantException {
		EntitledDto savedDto = null;
		try {
			notNull(entitledDto, "entitledDto must be set");
			Entitled entitled = entitledMapper.toNewEntity(entitledDto);
			entitled.setOrganizationUnit(organizationUnitService.getByCurrentUser());
			entitled = entitledRepository.save(entitled);

			// create ucm folder for every entitled
			String requestFolder = contentManager
					.getFolderIdFromPath("/" + entitledDto.getEntitledRegistrationDto().getRequest().getSerial() + "/");
			contentManager.createFolder(entitled.getId().toString(), true, requestFolder);

			savedDto = entitledMapper.toDto(entitled);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto update(EntitledDto entitledDto) throws JudicialWarrantException {
		EntitledDto updatedDto = null;
		try {
			notNull(entitledDto, "entitledDto must be set");
			Entitled entitled = entitledMapper.toEntity(entitledDto);
			entitled = entitledRepository.save(entitled);
			updatedDto = entitledMapper.toDto(entitled);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return updatedDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Set<EntitledDto> getAllByEntitledRegistrationSerial(String serial) throws JudicialWarrantException {
		Set<EntitledDto> entitledDtos = null;
		try {
			notNull(serial, "serial must be set");
			Set<Entitled> entitleds = entitledRepository.findByEntitledRegistrationRequestSerial(serial);
			entitledDtos = entitledMapper.toDto(entitleds);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return entitledDtos;
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

			Entitled entitled = entitledRepository.findById(id).get();

			entitledRepository.deleteById(id);

			// create ucm folder for every entitled
			String requestFolder = contentManager
					.getFolderIdFromPath("/" + entitled.getEntitledRegistration().getRequest().getSerial() + "/");
			contentManager.createFolder(id.toString(), true, requestFolder);

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
	public Set<Entitled> changeStatusForInternal(Set<Entitled> entities, EntitledStatusEnum entitledStatusEnum,
			String note) throws JudicialWarrantException {
		Set<Entitled> savedEntities = null;
		try {
			notNull(entities, "dto must be set");
			notNull(entitledStatusEnum, "entitledStatusEnum must be set");

			savedEntities = new HashSet<>();

			for (Entitled entity : entities) {
				savedEntities.add(changeStatus(entity, entitledStatusEnum, note));
			}

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return savedEntities;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto changeStatus(EntitledDto dto, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException {

		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto), entitledStatusEnum, note));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto accept(String serial, Long id, String note) throws JudicialWarrantException {
		EntitledDto dto = getById(id);
		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto), EntitledStatusEnum.ACCEPTED, note));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Set<EntitledDto> acceptAll(String serial, String note) throws JudicialWarrantException {
		Set<EntitledDto> dtos = getAllByEntitledRegistrationSerial(serial);
		dtos = changeStatus(dtos, EntitledStatusEnum.ACCEPTED, note);
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto reject(String serial, Long id, String note) throws JudicialWarrantException {
		EntitledDto dto = getById(id);
		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto), EntitledStatusEnum.ACCEPTED, note));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto inTraining(String serial, Long id, String note) throws JudicialWarrantException {
		EntitledDto dto = getById(id);
		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto), EntitledStatusEnum.TRAINNING, note));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto passed(String serial, Long id, String note) throws JudicialWarrantException {
		EntitledDto dto = getById(id);
		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto), EntitledStatusEnum.PASSED, note));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto fail(String serial, Long id, String note) throws JudicialWarrantException {
		EntitledDto dto = getById(id);
		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto), EntitledStatusEnum.FAILED, note));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntitledDto cardRecieved(String serial, Long id, String note) throws JudicialWarrantException {
		EntitledDto dto = getById(id);
		return entitledMapper.toDto(changeStatus(entitledMapper.toEntity(dto), EntitledStatusEnum.CARD_RECIEVED, note));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Entitled changeStatus(Entitled entity, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException {

		try {
			notNull(entity, "dto must be set");
			notNull(entity.getId(), "dto.id must be set");
			notNull(entitledStatusEnum, "entitledStatusEnum must be set");

			entitledWorkflowValidator.validate(entity, entitledStatusEnum);

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
	public Set<Entitled> changeStatusByEntitledRegistrationId(Long entitledRegistrationId,
			EntitledStatusEnum entitledStatusEnum, String note) throws JudicialWarrantException {
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

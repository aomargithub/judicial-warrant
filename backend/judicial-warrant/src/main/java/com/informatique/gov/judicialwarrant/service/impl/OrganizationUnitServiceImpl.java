package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.OrganizationUnitRepository;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.service.InternalOrganizationUnitService;
import com.informatique.gov.judicialwarrant.service.OrganizationUnitService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrganizationUnitServiceImpl implements OrganizationUnitService, InternalOrganizationUnitService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5559454993806874431L;

	private OrganizationUnitRepository organizationUnitRepository;
	private ModelMapper<OrganizationUnit, OrganizationUnitDto, Short> organizationUnitMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<OrganizationUnitDto> getAll() throws JudicialWarrantException {
		List<OrganizationUnitDto> dtos = null;
		try {
			List<OrganizationUnit> entities = organizationUnitRepository.findAll();
			dtos = organizationUnitMapper.toDto(entities);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public OrganizationUnitDto getById(Short id) throws JudicialWarrantException {
		OrganizationUnitDto dto = null;
		try {
			notNull(id, "id must be set");
			
			OrganizationUnit entity = organizationUnitRepository.findById(id).get();
			dto = organizationUnitMapper.toDto(entity);
			
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return dto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public Short getVersionById(Short id) throws JudicialWarrantException {
		Short version = null;
		try {
			notNull(id, "id must be set");
			version = organizationUnitRepository.findVersionById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
		return version;		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrganizationUnitDto save(final OrganizationUnitDto dto) throws JudicialWarrantException {
		OrganizationUnitDto savedDto = null;

		try {
			notNull(dto, "dto must be set");

			OrganizationUnit entiry = organizationUnitMapper.toNewEntity(dto);
			
			entiry = organizationUnitRepository.save(entiry);
			
			savedDto = organizationUnitMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrganizationUnitDto update(final OrganizationUnitDto dto) throws JudicialWarrantException {
		OrganizationUnitDto savedDto = null;

		try {
			notNull(dto, "dto must be set");			

			OrganizationUnit entiry = organizationUnitMapper.toEntity(dto);
			
			entiry = organizationUnitRepository.save(entiry);
			
			savedDto = organizationUnitMapper.toDto(entiry);

		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Short id) throws JudicialWarrantException {
		
		try {
			notNull(id, "id must be set");
			organizationUnitRepository.deleteById(id);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}
	
	/*@Override
	@Transactional(rollbackFor = Exception.class)
	public OrganizationUnitDto updateIfNotModified(final OrganizationUnitDto dto, Short id) throws JudicialWarrantException {
		OrganizationUnitDto savedDto = null;

		try {
			notNull(dto, "dto must be set");
			notNull(id, "id must be set");
			notNull(dto.getVersion(), "version of dto must be set");
			
			
			Short realVersion = organizationUnitRepository.findVersionById(id);
				
			if(realVersion == null) {
				throw new ResourceNotFoundException(id);
			}else {
				if(!realVersion.equals(dto.getVersion())) {
					throw new ResourceModifiedException(id, dto.getVersion(), realVersion);
				}
			}
			

			OrganizationUnit entiry = organizationUnitMapper.toNewEntity(dto);
			
			entiry.setId(id);
			
			entiry = organizationUnitRepository.save(entiry);
			
			savedDto = organizationUnitMapper.toDto(entiry);

		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return savedDto;
	}*/
}

package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Nationality;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.NationalityRepository;
import com.informatique.gov.judicialwarrant.rest.dto.NationalityDto;
import com.informatique.gov.judicialwarrant.service.NationalityService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NationalityServiceImpl implements NationalityService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6476773292451702937L;
	private NationalityRepository nationalityRepository;
    private ModelMapper<Nationality, NationalityDto, Short> mapper;
    
    
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public NationalityDto getByCode(String code) throws JudicialWarrantException {
    	Nationality entity = null;
    	NationalityDto dto = null;
        try{
            notNull(code, "code must be set");
            entity = nationalityRepository.findByCode(code);
            dto = mapper.toDto(entity);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<NationalityDto> getAll() throws JudicialWarrantException {
        List<NationalityDto> dtos = null;
        List<Nationality> entities = null;

        try{
            entities = nationalityRepository.findAll();
            dtos = mapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }

}

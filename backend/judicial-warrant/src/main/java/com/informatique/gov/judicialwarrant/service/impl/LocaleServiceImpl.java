package com.informatique.gov.judicialwarrant.service.impl;


import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.Locale;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.LocaleRepository;
import com.informatique.gov.judicialwarrant.rest.dto.LocaleDto;
import com.informatique.gov.judicialwarrant.service.LocaleService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocaleServiceImpl implements LocaleService{

    private static final long serialVersionUID = 5122481304765124811L;
    private LocaleRepository localeRepository;
    private ModelMapper<Locale, LocaleDto, Byte> localeMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public LocaleDto getByCode(String code) throws JudicialWarrantException {
    	LocaleDto dto = null;
        try{
            notNull(code, "code must be set");
            Locale entity = localeRepository.findByCode(code);
            dto = localeMapper.toDto(entity);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LocaleDto> getAll() throws JudicialWarrantException {
        List<LocaleDto> dtos = null;
        try{
        	List<Locale> entities = localeRepository.findAll();
            dtos = localeMapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LocaleDto> getActive() throws JudicialWarrantException {
        List<LocaleDto> dtos = null;
        
        try{
        	List<Locale> entities = localeRepository.findByIsActive(Boolean.TRUE);
            dtos = localeMapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LocaleDto> getInActive() throws JudicialWarrantException {
        List<LocaleDto> dtos = null;
        
        try{
        	List<Locale> entities = localeRepository.findByIsActive(Boolean.FALSE);
            dtos = localeMapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }
}

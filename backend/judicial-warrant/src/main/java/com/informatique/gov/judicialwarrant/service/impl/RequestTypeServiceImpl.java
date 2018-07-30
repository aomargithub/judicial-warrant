package com.informatique.gov.judicialwarrant.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestTypeRepository;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.service.RequestTypeService;
import com.informatique.gov.judicialwarrant.support.modelmpper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestTypeServiceImpl implements RequestTypeService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6476773292451702937L;
	private RequestTypeRepository requestTypeRepository;
    private ModelMapper<RequestType, RequestTypeDto, Byte> requestTypeMapper;
    
    
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public RequestTypeDto getByCode(String code) throws JudicialWarrantException {
    	RequestType entity = null;
    	RequestTypeDto dto = null;
        try{
            notNull(code, "code must be set");
            entity = requestTypeRepository.findByCode(code);
            dto = requestTypeMapper.toDto(entity);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<RequestTypeDto> getAll() throws JudicialWarrantException {
        List<RequestTypeDto> dtos = null;
        List<RequestType> entities = null;

        try{
            entities = requestTypeRepository.findAll();
            dtos = requestTypeMapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<RequestTypeDto> getActive() throws JudicialWarrantException {
        List<RequestTypeDto> dtos = null;
        List<RequestType> entities = null;

        try{
            entities = requestTypeRepository.findByIsActive(Boolean.TRUE);
            dtos = requestTypeMapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<RequestTypeDto> getInActive() throws JudicialWarrantException {
        List<RequestTypeDto> dtos = null;
        List<RequestType> entities = null;

        try{
            entities = requestTypeRepository.findByIsActive(Boolean.FALSE);
            dtos = requestTypeMapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<RequestTypeDto> getByIsActiveAndIsInternal(Boolean isActive, Boolean isInternal) throws JudicialWarrantException {
        List<RequestTypeDto> dtos = null;
        List<RequestType> entities = null;

        try{
            entities = requestTypeRepository.findByIsActiveAndIsInternal(isActive, isInternal);
            dtos = requestTypeMapper.toDto(entities);
        }catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }

        return dtos;
    }
}

package com.informatique.gov.judicialwarrant.service.impl;


import lombok.AllArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.ErrorLog;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.persistence.repository.ErrorLogRepository;
import com.informatique.gov.judicialwarrant.service.InternalErrorLogService;

import java.util.Date;

@Service
@AllArgsConstructor
public class ErrorLogServiceImpl implements InternalErrorLogService {

    private static final long serialVersionUID = -8524054168241823319L;
    private final static Logger logger = LogManager.getLogger(ErrorLogServiceImpl.class);
    private ErrorLogRepository errorLogRepository;


    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public JudicialWarrantException log(JudicialWarrantException judicialWarrantException){
        ErrorLog errorLog = null;
        Long errorId = null;

        try{

          //  ketabException.setUserLoginName(securityHolderService.getUserName());

            errorLog = new ErrorLog();
            errorLog.setCreateDate(new Date());
            errorLog.setUserName(judicialWarrantException.getUserDomainName());
            errorLog.setRequestId(judicialWarrantException.getRequestId());
            errorLog.setStackTrace(judicialWarrantException.toString());

            errorLog = errorLogRepository.save(errorLog);

            errorId = errorLog.getId();


            judicialWarrantException.setErrorId(errorId);


        }catch(Exception e){
            logger.error(e);
        }

        return judicialWarrantException;
    }
}
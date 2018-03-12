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
    public JudicialWarrantException log(JudicialWarrantException ketabException){
        ErrorLog errorLog = null;
        Long errorId = null;

        try{

          //  ketabException.setUserLoginName(securityHolderService.getUserName());

            errorLog = new ErrorLog();
            errorLog.setCreateDate(new Date());
            errorLog.setUserName(ketabException.getUserDomainName());
            errorLog.setRequestId(ketabException.getRequestId());
            errorLog.setStackTrace(ketabException.toString());

            errorLog = errorLogRepository.save(errorLog);

            errorId = errorLog.getId();


            ketabException.setErrorId(errorId);


        }catch(Exception e){
            logger.error(e);
        }

        return ketabException;
    }
}
package com.informatique.gov.judicialwarrant.service;


import java.io.Serializable;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface InternalErrorLogService extends Serializable {

    JudicialWarrantException log(JudicialWarrantException sakException);

}
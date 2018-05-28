package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledHistoryLog;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface InternalEntitledHistoryLogService extends Serializable{

	EntitledHistoryLog create(Entitled entitled, String note) throws JudicialWarrantException;

	EntitledHistoryLog create(Entitled entitled) throws JudicialWarrantException;

}

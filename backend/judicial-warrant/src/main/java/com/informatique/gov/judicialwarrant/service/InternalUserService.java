package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface InternalUserService extends Serializable{

	User getByLoginName(String loginName) throws JudicialWarrantException;

	User getByCurrentUser() throws JudicialWarrantException;

}

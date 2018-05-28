package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface InternalOrganizationUnitService extends Serializable{

	OrganizationUnit getByCurrentUser() throws JudicialWarrantException;

}

package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface InternalCapacityDelegationService extends Serializable{

	CapacityDelegation getIfValid(String serial) throws JudicialWarrantException;

}

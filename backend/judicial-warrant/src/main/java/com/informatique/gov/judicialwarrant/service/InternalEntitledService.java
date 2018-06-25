package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.Set;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;

public interface InternalEntitledService extends Serializable{

	Set<Entitled> changeStatusForInternal(Set<Entitled> entities, EntitledStatusEnum entitledStatusEnum, String note) throws JudicialWarrantException;
	Entitled changeStatus(Entitled entity, EntitledStatusEnum entitledStatusEnum, String note)
			throws JudicialWarrantException;
	Set<Entitled> changeStatusByEntitledRegistrationId(Long entitledRegistrationId, EntitledStatusEnum entitledStatusEnum,
			String note) throws JudicialWarrantException;

}

package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;
import java.util.Set;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledAttachment;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

public interface InternalEntitledAttachmentService extends Serializable{

	Set<EntitledAttachment> save(Set<EntitledAttachment> entities, Entitled entitled) throws JudicialWarrantException;

	Set<EntitledAttachment> create(Set<EntitledAttachment> entities, Entitled entitled) throws JudicialWarrantException;

}

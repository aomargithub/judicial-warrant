package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;

import com.informatique.gov.judicialwarrant.domain.DomainModel;

public interface IsActiveRepository<T extends DomainModel<?>>{
	List<T> findByIsActive(Boolean isActive);
}

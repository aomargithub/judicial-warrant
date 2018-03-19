package com.informatique.gov.judicialwarrant.persistence.repository;

import com.informatique.gov.judicialwarrant.domain.DomainModel;

public interface CodeRepository<T extends DomainModel<?>> {
	T findByCode(String code);
}

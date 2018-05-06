package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.RequestInternalStatus;

@Repository
public interface RequestInternalStatusRepository extends JpaRepository<RequestInternalStatus, Byte> {
	RequestInternalStatus findByCode(String code);

}

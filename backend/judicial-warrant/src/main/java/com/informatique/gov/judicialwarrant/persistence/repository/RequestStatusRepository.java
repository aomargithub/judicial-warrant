package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.RequestStatus;

@Repository
public interface RequestStatusRepository extends JpaRepository<RequestStatus, Byte> {
	RequestStatus findByCode(String code);

}

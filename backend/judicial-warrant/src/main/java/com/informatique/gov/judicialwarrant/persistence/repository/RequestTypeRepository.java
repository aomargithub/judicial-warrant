package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.RequestType;

@Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, Byte>{
	List<RequestType> findByIsActive(Boolean isActive);
	RequestType findByCode(String code);
}

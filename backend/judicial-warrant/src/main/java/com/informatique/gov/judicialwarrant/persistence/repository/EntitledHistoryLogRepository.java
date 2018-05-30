package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.EntitledHistoryLog;

@Repository
public interface EntitledHistoryLogRepository extends JpaRepository<EntitledHistoryLog, Long>{
	
	Integer deleteByEntitledEntitledRegistrationId(Long id);
	Integer deleteByEntitledId(Long entitledId);
	
}

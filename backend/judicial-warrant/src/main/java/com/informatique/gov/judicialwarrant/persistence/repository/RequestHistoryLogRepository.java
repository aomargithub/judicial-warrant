package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.RequestHistoryLog;
@Repository
public interface RequestHistoryLogRepository extends JpaRepository<RequestHistoryLog, Long>{

}

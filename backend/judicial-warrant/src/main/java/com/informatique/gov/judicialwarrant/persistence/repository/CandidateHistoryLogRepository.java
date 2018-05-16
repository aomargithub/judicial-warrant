package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.CandidateHistoryLog;

@Repository
public interface CandidateHistoryLogRepository extends JpaRepository<CandidateHistoryLog, Long>{
	
	void deleteByCandidateRequestId(Long id);
	
}

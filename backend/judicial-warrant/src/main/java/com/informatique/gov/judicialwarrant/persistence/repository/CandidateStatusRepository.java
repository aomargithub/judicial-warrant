package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.informatique.gov.judicialwarrant.domain.CandidateStatus;

@Repository
public interface CandidateStatusRepository extends JpaRepository<CandidateStatus, Byte>{
	
	CandidateStatus findByCode(String code);
	
}

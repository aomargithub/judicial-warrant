package com.informatique.gov.judicialwarrant.persistence.repository;



import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.Candidate;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	@Query("select version from Candidate c where c.id = :id")
	Short findVersionById(@Param("id") Long id);	
	
	void deleteCandidateByRequestId(Long id);
	
	Set<Candidate> getCandidatesByRequestId(Long id);

}

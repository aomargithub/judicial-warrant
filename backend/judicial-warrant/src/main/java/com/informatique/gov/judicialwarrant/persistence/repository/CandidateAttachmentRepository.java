package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.CandidateAttachment;
@Repository
public interface CandidateAttachmentRepository extends JpaRepository<CandidateAttachment, Long>{
	@Query("select version from CandidateAttachment ca where ca.id = :id")
	Short findVersionById(@Param("id") Long id);

}

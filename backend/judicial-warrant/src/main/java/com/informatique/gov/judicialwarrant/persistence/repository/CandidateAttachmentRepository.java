package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.CandidateAttachment;
@Repository
public interface CandidateAttachmentRepository extends JpaRepository<CandidateAttachment, Long>{
	@Query("select version from CandidateAttachment ca where ca.id = :id")
	Short findVersionById(@Param("id") Long id);
	@EntityGraph(value = "CandidateAttachment.fat", type = EntityGraphType.FETCH)
	Optional<CandidateAttachment> findById(Long id);

}

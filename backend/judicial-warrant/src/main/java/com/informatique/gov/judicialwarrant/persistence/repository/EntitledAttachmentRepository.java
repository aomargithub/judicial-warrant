package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.EntitledAttachment;
@Repository
public interface EntitledAttachmentRepository extends JpaRepository<EntitledAttachment, Long>{
	
	@Query("select version from EntitledAttachment ea where ea.id = :id")
	Short findVersionById(@Param("id") Long id);
	
	@EntityGraph(value = "EntitledAttachment.fat", type = EntityGraphType.FETCH)
	Optional<EntitledAttachment> findById(Long id);
	
	Integer deleteByEntitledEntitledRegistrationId(Long entitledRegistrationId);
	
	Integer deleteByEntitledId(Long entitledId);

}

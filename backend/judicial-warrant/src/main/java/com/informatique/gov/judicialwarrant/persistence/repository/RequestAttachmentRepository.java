package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;

import com.informatique.gov.judicialwarrant.domain.RequestAttachment;

public interface RequestAttachmentRepository extends JpaRepository<RequestAttachment, Long> {

	@EntityGraph(value = "RequestAttachment.fat", type = EntityGraphType.FETCH)
	List<RequestAttachment> findByRequestSerial(String serial);

	@Query("select version from RequestAttachment at where at.id = :id")
	Short findVersionById(@Param("id") Long id);

	@EntityGraph(value = "RequestAttachment.fat", type = EntityGraphType.FETCH)
	RequestAttachment findByIdAndRequestSerial(Long id, String serial);
}

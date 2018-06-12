package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.RequestTypeAttachmentType;

@Repository
public interface RequestTypeAttachmentTypeRepository extends JpaRepository<RequestTypeAttachmentType, Short>{
	@EntityGraph(value = "RequestTypeAttachmentType.fat", type = EntityGraphType.FETCH)
	List<RequestTypeAttachmentType> findAll();
	
	@EntityGraph(value = "RequestTypeAttachmentType.fat", type = EntityGraphType.FETCH)
	List<RequestTypeAttachmentType> findByRequestTypeId(Byte id);
	
	@Query("select version from RequestTypeAttachmentType rt where rt.id = :id")
	Short getVersionById(@Param("id") Short id);
	
	void deleteById(@Param("id") Short id);
	
}

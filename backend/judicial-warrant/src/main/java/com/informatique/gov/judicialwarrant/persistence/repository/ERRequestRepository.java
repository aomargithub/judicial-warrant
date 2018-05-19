package com.informatique.gov.judicialwarrant.persistence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.ERRequest;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

@Repository
public interface ERRequestRepository extends JpaRepository<ERRequest, Long>{
	
	@EntityGraph(value = "ERRequest.withRequest", type = EntityGraphType.FETCH)
	ERRequest findByRequestSerial(String serial) throws JudicialWarrantException;
	
	@EntityGraph(value = "ERRequest.withRequest", type = EntityGraphType.FETCH)
	ERRequest findByRequestSerialAndRequestOrganizationUnitId(String serial, Short id) throws JudicialWarrantException;
	
	@EntityGraph(value = "ERRequest.withRequest", type = EntityGraphType.FETCH)
	List<ERRequest> findAllByRequestOrganizationUnitId(Short organizationUnitId);
	
}

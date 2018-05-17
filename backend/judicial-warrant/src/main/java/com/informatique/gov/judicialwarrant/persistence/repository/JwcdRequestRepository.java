package com.informatique.gov.judicialwarrant.persistence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

@Repository
public interface JwcdRequestRepository extends JpaRepository<JwcdRequest, Integer>{
	
	@EntityGraph(value = "JwcdRequest.withRequest", type = EntityGraphType.FETCH)
	JwcdRequest findByRequestSerial(String serial) throws JudicialWarrantException;
	
	@EntityGraph(value = "JwcdRequest.withRequest", type = EntityGraphType.FETCH)
	JwcdRequest findByRequestSerialAndRequestOrganizationUnitId(String serial, Short organizationUnitId) throws JudicialWarrantException;
	
	@EntityGraph(value = "ERRequest.withRequest", type = EntityGraphType.FETCH)
	List<JwcdRequest> findAllByRequestOrganizationUnitId(Short organizationUnitId);
	
}

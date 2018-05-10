package com.informatique.gov.judicialwarrant.persistence.repository;


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
	
}

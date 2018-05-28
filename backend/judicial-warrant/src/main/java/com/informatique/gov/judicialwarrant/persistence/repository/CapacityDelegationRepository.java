package com.informatique.gov.judicialwarrant.persistence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;

@Repository
public interface CapacityDelegationRepository extends JpaRepository<CapacityDelegation, Long>{
	
	Short findVersionByRequestSerial(String serial);
	
	Short findVersionByRequestSerialAndRequestOrganizationUnitId(String serial, Short organizationUnitId);
	
	@EntityGraph(value = "CapacityDelegation.fat", type = EntityGraphType.FETCH)
	CapacityDelegation findByRequestSerial(String serial);
	
	@EntityGraph(value = "CapacityDelegation.fat", type = EntityGraphType.FETCH)
	CapacityDelegation findByRequestSerialAndRequestOrganizationUnitId(String serial, Short organizationUnitId);
	
	@EntityGraph(value = "CapacityDelegation.fat", type = EntityGraphType.FETCH)
	List<CapacityDelegation> findByRequestOrganizationUnitId(Short organizationUnitId);
	
}

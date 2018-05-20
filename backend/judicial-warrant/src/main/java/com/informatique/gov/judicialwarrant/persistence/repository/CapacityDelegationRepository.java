package com.informatique.gov.judicialwarrant.persistence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;

@Repository
public interface CapacityDelegationRepository extends JpaRepository<CapacityDelegation, Long>{
	
	@Query("select version from CapacityDelegation cd where cd.request.serial = :serial")
	Short findVersionBySerial(@Param("serial") String serial);
	
	@Query("select version from CapacityDelegation cd where cd.request.serial = :serial and cd.request.organizationUnit.id = :organizationUnitId")
	Short findVersionBySerialAndRequestOrganizationUnitId(@Param("serial") String serial, @Param("organizationUnitId") Short organizationUnitId);
	
	@EntityGraph(value = "CapacityDelegation.withRequest", type = EntityGraphType.FETCH)
	CapacityDelegation findByRequestSerial(String serial);
	
	@EntityGraph(value = "CapacityDelegation.withRequest", type = EntityGraphType.FETCH)
	CapacityDelegation findByRequestSerialAndRequestOrganizationUnitId(String serial, Short organizationUnitId);
	
	@EntityGraph(value = "CapacityDelegation.withRequest", type = EntityGraphType.FETCH)
	List<CapacityDelegation> findByRequestOrganizationUnitId(Short organizationUnitId);
	
}

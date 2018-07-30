package com.informatique.gov.judicialwarrant.persistence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;

@Repository
public interface CapacityDelegationRepository extends JpaRepository<CapacityDelegation, Long>{
	
	@Query("select version from CapacityDelegation cd where cd.request.serial = :serial")
	Short findVersionByRequestSerial(@Param("serial") String serial);
	
	Short findVersionByRequestSerialAndRequestOrganizationUnitId(String serial, Short organizationUnitId);
	
	@EntityGraph(value = "CapacityDelegation.fat", type = EntityGraphType.FETCH)
	CapacityDelegation findByRequestSerial(String serial);
	
	@EntityGraph(value = "CapacityDelegation.fat", type = EntityGraphType.FETCH)
	CapacityDelegation findByRequestSerialAndRequestOrganizationUnitId(String serial, Short organizationUnitId);
	
	@EntityGraph(value = "CapacityDelegation.fat", type = EntityGraphType.FETCH)
	@Query("from CapacityDelegation cd where (:status is null or cd.request.currentStatus.code = :status) and ( :organizationUnitId is null or cd.request.organizationUnit.id = :organizationUnitId)")
	List<CapacityDelegation> findByRequestCurrentStatusCodeAndOrganizationUnitId(@Param("status") String status, @Param("organizationUnitId") Short organizationUnitId);
	
}

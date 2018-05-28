package com.informatique.gov.judicialwarrant.persistence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.EntitledRegistration;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

@Repository
public interface EntitledRegistrationRepository extends JpaRepository<EntitledRegistration, Long>{
	
	@EntityGraph(value = "EntitledRegistration.fat", type = EntityGraphType.FETCH)
	EntitledRegistration findByRequestSerial(String serial) throws JudicialWarrantException;
	
	@EntityGraph(value = "EntitledRegistration.fat", type = EntityGraphType.FETCH)
	EntitledRegistration findByRequestSerialAndRequestOrganizationUnitId(String serial, Short id) throws JudicialWarrantException;
	
	@EntityGraph(value = "EntitledRegistration.fat", type = EntityGraphType.FETCH)
	List<EntitledRegistration> findAllByRequestOrganizationUnitId(Short organizationUnitId);
	
	@EntityGraph(value = "EntitledRegistration.fat", type = EntityGraphType.FETCH)
	List<EntitledRegistration> findByRequestOrganizationUnitId(Short organizationUnitId);
	
}

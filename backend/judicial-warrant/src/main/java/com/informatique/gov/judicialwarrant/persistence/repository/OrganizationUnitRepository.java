package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.OrganizationUnit;

@Repository
public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnit, Short>{

	@Query("select version from OrganizationUnit ou where ou.id = :id")
	Short findVersionById(@Param("id") Short id);
}

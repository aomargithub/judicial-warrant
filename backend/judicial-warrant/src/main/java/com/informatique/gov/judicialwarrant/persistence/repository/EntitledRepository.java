package com.informatique.gov.judicialwarrant.persistence.repository;



import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.Entitled;
@Repository
public interface EntitledRepository extends JpaRepository<Entitled, Long>{
	
	@Query("select version from Entitled e where e.id = :id")
	Short findVersionById(@Param("id") Long id);
	
	Entitled findByIdAndVersion(Long id, Short version);	
	
	Integer deleteByEntitledRegistrationId(Long id);
	
	Set<Entitled> findByEntitledRegistrationId(Long id);

}

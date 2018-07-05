package com.informatique.gov.judicialwarrant.persistence.repository;



import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.Entitled;
@Repository
public interface EntitledRepository extends JpaRepository<Entitled, Long>{
	
	@Query("select version from Entitled e where e.id = :id")
	Short findVersionById(@Param("id") Long id);
	
	@EntityGraph(value = "Entitled.fat", type = EntityGraphType.FETCH)
	Optional<Entitled> findById(Long id);
	
	@EntityGraph(value = "Entitled.fat", type = EntityGraphType.FETCH)
	Entitled findByIdAndVersion(Long id, Short version);	
	
	Integer deleteByEntitledRegistrationId(Long id);
	
	@EntityGraph(value = "Entitled.fat", type = EntityGraphType.FETCH)
	Set<Entitled> findByEntitledRegistrationId(Long id);
	
	@EntityGraph(value = "Entitled.fat", type = EntityGraphType.FETCH)
	Set<Entitled> findByEntitledRegistrationRequestSerial(String serial);
	
	@EntityGraph(value = "Entitled.fat", type = EntityGraphType.FETCH)
	Set<Entitled> findByEntitledRegistrationRequestSerialAndCurrentStatusCodeIn(String serial, List<String> statusCodes);

}

package com.informatique.gov.judicialwarrant.persistence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.Request;
import com.informatique.gov.judicialwarrant.domain.RequestType;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
	
	@EntityGraph(value = "Request.fat", type = EntityGraphType.FETCH)
	Request findBySerial(String serial);
	
	List<Request> findByType(RequestType requestType);
	
	@Query("select version from Request req where req.serial = :serial")
	Short findVersionBySerial(@Param("serial") String serial);
	
}

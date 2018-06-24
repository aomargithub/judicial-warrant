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
public interface RequestRepository extends JpaRepository<Request, Long> {

	@EntityGraph(value = "Request.fat", type = EntityGraphType.FETCH)
	Request findBySerial(String serial);

	List<Request> findByType(RequestType requestType);

	@EntityGraph(value = "Request.fat", type = EntityGraphType.FETCH)
	@Query("select req from Request req left join req.type  t left join req.currentInternalStatus cis left join req.currentStatus cs left join req.organizationUnit ou  where (:typeCode is null or t.code = :typeCode) and (:currentInternalStatusCode is null or cis.code = :currentInternalStatusCode) and (:currentStatusCode is null or cs.code = :currentStatusCode) and (:organizationId is null or ou.id = :organizationId)")
	List<Request> findByTypeCodeAndCurrentInternalStatusAndCurrentStatus(@Param("typeCode") String typeCode,
			@Param("currentInternalStatusCode") String currentInternalStatusCode,
			@Param("currentStatusCode") String currentStatusCode, @Param("organizationId") Short organizationId);

	@Query("select version from Request req where req.serial = :serial")
	Short findVersionBySerial(@Param("serial") String serial);

}

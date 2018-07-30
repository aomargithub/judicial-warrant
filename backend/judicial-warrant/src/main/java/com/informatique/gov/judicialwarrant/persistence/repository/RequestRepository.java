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
	@Query("from Request r where (:organizationUnitId is null or r.organizationUnit.id = :organizationUnitId) and (:requestTypeCode is null or r.type.code = :requestTypeCode) and (:currentInternalStatusCode is null or r.currentInternalStatus.code = :currentInternalStatusCode) and not (r.currentInternalStatus.code = :execludeCurrentInternalStatusCode and r.type.code = :execludeRequestTypeCode)")
	List<Request> findByRequestTypeCodeAndCurrentStatus(@Param("requestTypeCode") String requestTypeCode, @Param("currentInternalStatusCode") String currentInternalStatusCode, @Param("organizationUnitId") Short organizationUnitId, @Param("execludeRequestTypeCode") String execludeRequestTypeCode, @Param("execludeCurrentInternalStatusCode") String execludeCurrentInternalStatusCode);


	@EntityGraph(value = "Request.fat", type = EntityGraphType.FETCH)
	@Query("from Request r where (:organizationUnitId is null or r.organizationUnit.id = :organizationUnitId) and (:requestTypeCode is null or r.type.code = :requestTypeCode) and (:currentStatusCode is null or r.currentStatus.code = :currentStatusCode) and r.type.code != :execludeRequestTypeCode")
	List<Request> findByRequestTypeCodeAndCurrentStatus(@Param("requestTypeCode") String requestTypeCode, @Param("currentStatusCode") String currentStatusCode, @Param("organizationUnitId") Short organizationUnitId, @Param("execludeRequestTypeCode") String execludeRequestTypeCode);

	
	@Query("select version from Request req where req.serial = :serial")
	Short findVersionBySerial(@Param("serial") String serial);

}

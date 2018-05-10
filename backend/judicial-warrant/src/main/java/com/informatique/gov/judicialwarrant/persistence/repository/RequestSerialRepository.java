package com.informatique.gov.judicialwarrant.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.RequestSerial;
import com.informatique.gov.judicialwarrant.domain.RequestType;

@Repository
public interface RequestSerialRepository extends JpaRepository<RequestSerial, Integer>{
	RequestSerial findByRequestTypeAndYear(RequestType requestType, Integer year);
}

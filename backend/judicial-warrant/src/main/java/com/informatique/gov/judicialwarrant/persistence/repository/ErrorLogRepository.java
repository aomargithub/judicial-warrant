package com.informatique.gov.judicialwarrant.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.ErrorLog;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long>{
}

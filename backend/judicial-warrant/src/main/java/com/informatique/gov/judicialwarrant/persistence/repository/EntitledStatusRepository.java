package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.informatique.gov.judicialwarrant.domain.EntitledStatus;

@Repository
public interface EntitledStatusRepository extends JpaRepository<EntitledStatus, Byte>{
	
	EntitledStatus findByCode(String code);
	
}

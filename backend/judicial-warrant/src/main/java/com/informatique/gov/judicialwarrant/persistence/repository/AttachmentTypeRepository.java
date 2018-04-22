package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.informatique.gov.judicialwarrant.domain.AttachmentType;

@Repository
public interface AttachmentTypeRepository extends JpaRepository<AttachmentType, Byte>{
	
	@Query("select version from AttachmentType at where at.id = :id")
	Short findVersionById(@Param("id") Byte id);

}

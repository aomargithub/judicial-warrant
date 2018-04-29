package com.informatique.gov.judicialwarrant.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.Nationality;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Byte>{

	Nationality findByCode(String code);
}

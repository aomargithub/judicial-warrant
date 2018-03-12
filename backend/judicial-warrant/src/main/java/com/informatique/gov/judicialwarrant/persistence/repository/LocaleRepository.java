package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.Locale;

@Repository
public interface LocaleRepository extends JpaRepository<Locale, Byte> {
	List<Locale> findByIsActive(Boolean isActive);
	Locale findByCode(String code);
}

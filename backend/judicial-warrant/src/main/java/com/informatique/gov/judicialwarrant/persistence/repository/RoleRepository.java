package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.informatique.gov.judicialwarrant.domain.Role;


public interface RoleRepository  extends JpaRepository<Role, Byte>{
	Role findByCode(String code);
	List<Role> findByIsInternal(Boolean isInternal);
	Role findByLdapSecurityGroup(String ldapSecurityGroup);
}

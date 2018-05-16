package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.UserCredentials;

@Repository
public interface UserCredientialRepository extends JpaRepository<UserCredentials, Integer>{

}

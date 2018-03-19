package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@EntityGraph(value = "User.fat", type = EntityGraphType.FETCH)
	User findByLoginNameIgnoreCase(String loginName);
}

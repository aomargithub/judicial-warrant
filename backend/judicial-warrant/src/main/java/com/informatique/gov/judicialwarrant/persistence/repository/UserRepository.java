package com.informatique.gov.judicialwarrant.persistence.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@EntityGraph(value = "User.fat", type = EntityGraphType.FETCH)
	User findByLoginNameIgnoreCase(String loginName);
	@Query("select u from User u where u.id != :id and u.civilId =:civilId")
	User findByCivilId(@Param("civilId")Long civilId,@Param("id")Integer id);
	
	@Query("select u from User u where u.id != :id and u.emailAddress=:emailAddress")
	User findByEmailAddress(@Param("emailAddress")String emailAddress,@Param("id")Integer id);
	
	@Query("select version from User at where at.id = :id")
	Short findVersionById(@Param("id") Integer id);
	
	@Query("select u from User u where u.id != :id and u.mobileNumber1=:mobileNumber")
	User findByMobileNumber1(@Param("mobileNumber")String mobileNumber,@Param("id")Integer id);
	
	@Query("select u from User u where u.id != :id and u.loginName=:loginName")
	User findByLoginName(@Param("loginName")String loginName,@Param("id")Integer id);
}

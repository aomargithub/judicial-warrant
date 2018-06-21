package com.informatique.gov.judicialwarrant.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.informatique.gov.judicialwarrant.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@EntityGraph(value = "User.fat", type = EntityGraphType.FETCH)
	User findByLoginNameIgnoreCase(String loginName);
	@Query("select id from User u where u.id != :id and u.civilId =:civilId")
	Integer findIdByCivilIdAndNotEqualId(@Param("civilId")Long civilId,@Param("id")Integer id);
	
	@Query("select id from User u where u.id != :id and u.emailAddress=:emailAddress")
	Integer findIdByEmailAddressAndNotEqualId(@Param("emailAddress")String emailAddress,@Param("id")Integer id);
	
	@Query("select version from User at where at.id = :id")
	Short findVersionById(@Param("id") Integer id);
	
	@Query("select id from User u where u.id != :id and u.mobileNumber1=:mobileNumber1")
	Integer findIdByMobileNumber1AndNotEqualId(@Param("mobileNumber1")String mobileNumber1,@Param("id")Integer id);
	
	@Query("select id from User u where u.id != :id and u.loginName=:loginName")
	Integer findIdByLoginNameAndNotEqualId(@Param("loginName")String loginName,@Param("id")Integer id);
	
	@Query("select id from User u where u.mobileNumber1=:mobileNumber1")
	Integer findIdByMobileNumber1(@Param("mobileNumber1")String mobileNumber1);
	@Query("select id from User u where u.loginName=:loginName")
	Integer findIdByLoginName(@Param("loginName")String loginName);
	@Query("select id from User u where u.civilId =:civilId")
	Integer findIdByCivilId(@Param("civilId")Long civilId);
	@Query("select id from User u where u.emailAddress=:emailAddress")
	Integer findIdByEmailAddress(@Param("emailAddress")String emailAddress);
	
	@EntityGraph(value = "User.fat", type = EntityGraphType.FETCH)
    List<User> findByUserTypeCode(String userTypeCode);
    @EntityGraph(value = "User.fat", type = EntityGraphType.FETCH)
    List<User> findAll();
    
    @EntityGraph(value = "User.fat", type = EntityGraphType.FETCH)
    Optional<User> findById(Integer id) ;
    
    @EntityGraph(value = "User.fat", type = EntityGraphType.FETCH)
    List<User> findByRoleIsInternal(Boolean isInternal);
    	
    
	 
}

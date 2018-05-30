package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "APP_USER_TYPE")
@Data
@ToString(of = {"id", "code"})
@EqualsAndHashCode(of = {"id", "code"}, callSuper = false)
public class UserType extends DomainEntity<Integer> implements CreationAuditable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5969600645584816417L;
	@Id
    @Column(name = "ID")
	private Integer id;
	
	@Column(name = "CODE")
	private String code;
	
}

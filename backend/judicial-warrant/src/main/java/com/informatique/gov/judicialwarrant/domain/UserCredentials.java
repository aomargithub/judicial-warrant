package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Entity
@Table(name = "APP_USER_CREDENTIALS")
@Data
@ToString(of = {"id", "password"})
@EqualsAndHashCode(of = {"id", "password"}, callSuper = false)
public class UserCredentials  extends DomainEntity<Integer> implements CreationAuditable{/**
	 * 
	 */
	private static final long serialVersionUID = -3300648653855789902L;
	@Id
	@Column(name="ID")
	private Integer id;
	@Column(name="PASSWORD")
	private String password;
	
	@OneToOne
	@JoinColumn(name = "ID")
	private User  user;

}

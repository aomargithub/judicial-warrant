package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
//	@SequenceGenerator(name = "User_Creds_Sequence", sequenceName = "app_user_creds_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Creds_Sequence")
	@Column(name="id")
	private Integer id;
	@Column(name="PASSWORD")
	private String password;

}

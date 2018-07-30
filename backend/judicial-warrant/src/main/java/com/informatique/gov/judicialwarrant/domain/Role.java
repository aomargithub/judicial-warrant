package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "APP_ROLE")
@Data
@ToString(of = {"id", "code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class Role extends DomainEntity<Byte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5650702934453689747L;

	@Id
    @Column(name = "id")
    private Byte id;
	
	@NaturalId
	@Column(name = "code")
	private String code;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Column(name = "is_internal")
	private Boolean isInternal;
	
	@Embedded
	private CreateLog createLog;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name = "LIST_ORDER")
	private Byte listOrder;
	
	@Column(name = "LDAP_SECURITY_GROUP")
	private String ldapSecurityGroup;
}

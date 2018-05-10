package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.NaturalId;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "APP_USER")
@Data
@ToString(of = {"id", "loginName", "civilId"})
@EqualsAndHashCode(of = {"loginName", "civilId"}, callSuper = false)

@NamedEntityGraphs({
	@NamedEntityGraph(name = "User.fat",
					  attributeNodes = {
							  @NamedAttributeNode(value = "organizationUnit"),
							  @NamedAttributeNode(value = "role")
					  })
})
public class User extends DomainEntity<Integer> implements CreationAuditable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8415889490581393136L;
	
	@Id
    @SequenceGenerator(name = "UserSequence", sequenceName = "app_user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Embedded
	private CreateLog createLog;
	
	@Embedded
	private UpdateLog updateLog;
	
	@Version
	@Column(name="VERSION")
	private Short version;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="MOBILE_NUMBER1")
	private String mobileNumber1;
	
	@Column(name="MOBILE_NUMBER2")
	private String mobileNumber2;
	
	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_UNIT_ID")
	private OrganizationUnit organizationUnit;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	private Role role;
	
	@Column(name = "CIVIL_ID")
	private Long civilId;
	
	@NaturalId
	@Column(name = "LOGIN_NAME")
	private String loginName;

}

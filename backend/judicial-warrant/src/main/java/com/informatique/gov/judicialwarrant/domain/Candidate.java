package com.informatique.gov.judicialwarrant.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "CANDIDATE")
@Data
@ToString(of = {"id", "civilId", "request", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"civilId", "request"}, callSuper = false)
public class Candidate extends DomainEntity<Long> implements CreationAuditable, UpdateAuditable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7323040331827005680L;
	
	@Id
    @SequenceGenerator(name = "CandidateSequence", sequenceName = "candidate_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CandidateSequence")
    @Column(name = "id")
    private Long id;
	
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Column(name = "CIVIL_ID")
	private Long civilId;
	
	@Version
	@Column(name="VERSION")
	private Short version;
	
	@Embedded
	private CreateLog createLog;
	
	@Embedded
	private UpdateLog updateLog;
	
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
	@JoinColumn(name = "CURRENT_STATUS_ID")
	private CandidateStatus currentStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REQUEST_ID")
	private Request request;
	
	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
	private List<CandidateHistoryLog> histortyLogs;
	

}

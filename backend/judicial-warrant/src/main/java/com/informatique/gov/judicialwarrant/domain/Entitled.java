package com.informatique.gov.judicialwarrant.domain;

import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "ENTITLED")
@Data
@ToString(of = {"id", "civilId", "entitledRegistration", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"civilId", "entitledRegistration"}, callSuper = false)
@NamedEntityGraphs({
	@NamedEntityGraph(name = "Entitled.fat",
					  attributeNodes = {
							  @NamedAttributeNode(value = "entitledRegistration"),
							  @NamedAttributeNode(value = "currentStatus")
					  })
})
public class Entitled extends DomainEntity<Long> implements CreationAuditable, UpdateAuditable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7323040331827005680L;
	
	@Id
    @SequenceGenerator(name = "EntitledSequence", sequenceName = "ENTITLED_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EntitledSequence")
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
	@JoinColumn(name = "ORGANIZATION_UNIT_ID", updatable = false)
	private OrganizationUnit organizationUnit;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CURRENT_STATUS_ID")
	private EntitledStatus currentStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ENTITLED_REGISTRATION_ID", updatable = false)
	private EntitledRegistration entitledRegistration;
	
	@OneToMany(mappedBy = "entitled", fetch = FetchType.LAZY)
	private List<EntitledHistoryLog> histortyLogs;
	
	@OneToMany(mappedBy = "entitled", fetch = FetchType.LAZY)
	private Set<EntitledAttachment> attachments;
	

}

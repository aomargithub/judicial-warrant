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

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "REQUEST")
@Data
@ToString(of = {"id", "number"})
@EqualsAndHashCode(of = {"number"}, callSuper = false)
public class Request extends DomainEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	@Id
    @SequenceGenerator(name = "RequestSequence", sequenceName = "request_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequestSequence")
    @Column(name = "id")
    private Integer id;
	
	@Version
	@Column(name="orm_version")
	private Short ormVersion;
	
	@NaturalId
	@Column(name="NUMBER", updatable = false)
	private String number;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID")
	private RequestType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENT_STATUS_ID")
	private RequestStatus currentStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_UNIT_ID")
	private OrganizationUnit organizationUnit;
	
	@OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
	private List<RequestHistoryLog> histortyLogs;
	
	@Embedded
	private CreateLog createLog;
	
	@Embedded
	private UpdateLog updateLog;	

}

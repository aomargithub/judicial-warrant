package com.informatique.gov.judicialwarrant.domain;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "REQUEST")
@Data
@ToString(of = {"id", "serial"})
@EqualsAndHashCode(of = {"serial"}, callSuper = false)
@NamedEntityGraphs({
	@NamedEntityGraph(name = "Request.req",
					  attributeNodes = {
							  @NamedAttributeNode(value = "type"),
							  @NamedAttributeNode(value = "currentInternalStatus"),
							  @NamedAttributeNode(value = "currentStatus"),
							  @NamedAttributeNode(value = "organizationUnit")
					  })
})
public class Request extends DomainEntity<Long> implements CreationAuditable, UpdateAuditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	@Id
    @SequenceGenerator(name = "RequestSequence", sequenceName = "request_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequestSequence")
    @Column(name = "id")
    private Long id;
	
	@Version
	@Column(name="VERSION")
	private Short version;
	
	@NaturalId
	@Column(name="SERIAL", updatable = false)
	private String serial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID")
	private RequestType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENT_INTERNAL_STATUS_ID")
	private RequestInternalStatus currentInternalStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENT_STATUS_ID")
	private RequestStatus currentStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORGANIZATION_UNIT_ID")
	private OrganizationUnit organizationUnit;
	
	@OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
	private List<RequestHistoryLog> histortyLogs;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JWCD_REQUEST_ID")
	private JwcdRequest jwcdRequest;
	
	@Embedded
	private CreateLog createLog;
	
	@Embedded
	private UpdateLog updateLog;	

}

package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "JWCD_REQUEST")
@Data
@ToString(of = {"id", "jobTitle"})
@EqualsAndHashCode(of = {"request"}, callSuper = false)
@NamedEntityGraphs({
	@NamedEntityGraph(name = "JwcdRequest.withRequest",
					  attributeNodes = {
							  @NamedAttributeNode(value = "request", subgraph = "JwcdRequest.request.fat")
					  }, 
					  subgraphs = {
							  @NamedSubgraph(name = "JwcdRequest.request.fat", 
							  attributeNodes = {
									  @NamedAttributeNode("type"),
									  @NamedAttributeNode("currentInternalStatus"),
									  @NamedAttributeNode("currentStatus"),
									  @NamedAttributeNode("organizationUnit")
									  }
							  )
					  }
			)
})
public class JwcdRequest extends DomainEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	@Id
    @SequenceGenerator(name = "JwcdRequestSequence", sequenceName = "Jwcd_request_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JwcdRequestSequence")
    @Column(name = "ID")
    private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUEST_ID")
	private Request request;
	
	@Column(name="JOB_TITLE")
	private String jobTitle;

}

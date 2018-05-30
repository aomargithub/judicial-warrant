package com.informatique.gov.judicialwarrant.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "ENTITLED_REGISTRATION")
@Data
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"request"}, callSuper = false)
@NamedEntityGraphs({
	@NamedEntityGraph(name = "EntitledRegistration.fat",
					  attributeNodes = {
							  @NamedAttributeNode(value = "capacityDelegation"),
							  @NamedAttributeNode(value = "request", subgraph = "Request.fat")
					  }, 
					  subgraphs = {
							  @NamedSubgraph(name = "Request.fat", 
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
public class EntitledRegistration extends DomainEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3785896982486708553L;

	@Id
    @Column(name = "ID")
    private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	@MapsId
	private Request request;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CAPACITY_DELEGATION_ID")
	private CapacityDelegation capacityDelegation;
	
	@OneToMany(mappedBy= "entitledRegistration",  fetch=FetchType.LAZY)
	private Set<Entitled> entitled;

}

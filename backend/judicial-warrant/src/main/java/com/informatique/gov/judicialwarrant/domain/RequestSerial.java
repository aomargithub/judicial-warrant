package com.informatique.gov.judicialwarrant.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "REQUEST_SERIAL")
@Data
@ToString(of = {"id"})
@EqualsAndHashCode(callSuper=false)
public class RequestSerial extends DomainEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	@Id
    @SequenceGenerator(name = "RequestSerialSequence", sequenceName = "request_serial_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequestSerialSequence")
    @Column(name = "ID")
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUEST_TYPE_ID")
	private RequestType requestType;
	
	@Column(name="YEAR")
	private Integer year;
	
	@Version
	@Column(name="VERSION")
	private Long version;
	
	@Column(name="LAST_NUMBER")
	private Integer lastNumber;

}

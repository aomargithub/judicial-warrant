package com.informatique.gov.judicialwarrant.domain;

import java.util.Date;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "REQUEST_HISTORY_LOG")
@Data
@ToString(of = {"id", "request", "status"})
@EqualsAndHashCode(of = {"request", "status"}, callSuper = false)
public class RequestHistoryLog extends DomainEntity<Long> implements CreationAuditable, UpdateAuditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 854525152394151545L;
	@Id
    @SequenceGenerator(name = "RequestHistoryLogSequence", sequenceName = "request_history_log_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequestHistoryLogSequence")
    @Column(name = "id")
    private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REQUEST_ID")
	private Request request;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERNAL_STATUS_ID")
	private RequestInternalStatus internalStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REQUEST_STATUS_ID")
	private RequestStatus status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CREATE_BY")
	private User createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "NOTE")
	private String note;
}

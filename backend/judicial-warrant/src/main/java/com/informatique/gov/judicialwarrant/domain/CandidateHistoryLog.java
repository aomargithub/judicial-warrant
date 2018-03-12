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
@Table(name = "CANDIDATE_HISTORY_LOG")
@Data
@ToString(of = {"id", "candidate", "status"})
@EqualsAndHashCode(of = {"candidate", "status"}, callSuper = false)
public class CandidateHistoryLog extends DomainEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9149014565684009065L;

	@Id
    @SequenceGenerator(name = "CandidateHistoryLogSequence", sequenceName = "candidate_history_log_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CandidateHistoryLogSequence")
    @Column(name = "id")
    private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CANDIDATE_ID")
	private Candidate candidate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CANDIDATE_STATUS_ID")
	private CandidateStatus status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CREATE_BY")
	private User createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "NOTE")
	private String note;
}

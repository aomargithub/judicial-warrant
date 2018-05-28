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
@Table(name = "ENTITLED_HISTORY_LOG")
@Data
@ToString(of = {"id", "entitled", "status"})
@EqualsAndHashCode(of = {"entitled", "status"}, callSuper = false)
public class EntitledHistoryLog extends DomainEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9149014565684009065L;

	@Id
    @SequenceGenerator(name = "EntitledHistoryLogSequence", sequenceName = "ENTITLED_HISTORY_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EntitledHistoryLogSequence")
    @Column(name = "id")
    private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ENTITLED_ID")
	private Entitled entitled;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ENTITLED_STATUS_ID")
	private EntitledStatus status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CREATE_BY")
	private User createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "NOTE")
	private String note;
}

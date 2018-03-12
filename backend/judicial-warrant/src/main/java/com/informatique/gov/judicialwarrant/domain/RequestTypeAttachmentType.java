package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "REQUEST_TYPE_ATTACHMENT_TYPE")
@Data
@ToString(of = {"id", "requestType", "attachmentType"})
@EqualsAndHashCode(of = {"requestType", "attachmentType"}, callSuper = false)
public class RequestTypeAttachmentType extends DomainEntity<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6271858078928664072L;
	
	@Id
    @SequenceGenerator(name = "RequestTypeAttachmentTypeSequence", sequenceName = "rqust_type_attachment_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequestTypeAttachmentTypeSequence")
    @Column(name = "id")
    private Short id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="REQUEST_TYPE_ID")
	private RequestType requestType;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ATTACHMENT_TYPE_ID")
	private AttachmentType attachmentType;
	
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@Embedded
	private CreateLog createLog;
	
	@Column(name = "LIST_ORDER")
	private Byte listOrder;
}

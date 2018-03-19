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

import org.springframework.data.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "REQUEST_ATTACHMENT")
@Data
@ToString(of = {"id", "request", "attachmentType"})
@EqualsAndHashCode(of = {"request", "attachmentType"}, callSuper = false)
public class RequestAttachment extends DomainEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3565672297335548741L;

	@Id
    @SequenceGenerator(name = "RequestAttachmentSequence", sequenceName = "request_attachment_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequestAttachmentSequence")
    @Column(name = "id")
    private Long id;
	
	@Version
	@Column(name="VERSION")
	private Short version;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REQUEST_ID")
	private Request request;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ATTACHMENT_TYPE_ID")
	private AttachmentType attachmentType;
	
	@Column(name = "UCM_DOCUMENT_ID")
	private String ucmDocumentId;
	
	@Embedded
	private CreateLog createLog;
	
	@Embedded
	private UpdateLog updateLog;
}

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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "ENTITLED_ATTACHMENT")
@Data
@ToString(of = {"id", "entitled", "attachmentType"})
@EqualsAndHashCode(of = {"entitled", "attachmentType"}, callSuper = false)
@NamedEntityGraphs({
	@NamedEntityGraph(name = "EntitledAttachment.fat",
					  attributeNodes = {
							  @NamedAttributeNode(value = "attachmentType"),
							  @NamedAttributeNode(value = "entitled")
					  })
})
public class EntitledAttachment extends DomainEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1856946362391799061L;
	
	@Id
    @SequenceGenerator(name = "EntitledAttachmentSequence", sequenceName = "ENTITLED_ATTACHMENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EntitledAttachmentSequence")
    @Column(name = "id")
    private Long id;
	
	@Version
	@Column(name="VERSION")
	private Short version;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ENTITLED_ID")
	private Entitled entitled;
	
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
package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "ATTACHMENT_TYPE")
@Data
@ToString(of = {"id", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"arabicName", "englishName"}, callSuper = false)
public class AttachmentType extends DomainEntity<Long> implements CreationAuditable, UpdateAuditable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6131509027915511341L;

	@Id
    @SequenceGenerator(name = "AttachmentTypeSequence", sequenceName = "attachment_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AttachmentTypeSequence")
    @Column(name = "id")
    private Long id;
	
	@Version
	@Column(name="VERSION")
	private Short version;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name = "IS_ENTITLED_ATTACHMENT")
	private Boolean isEntitledAttachment;
	
	@Column(name = "IS_MANDATORY")
	private Boolean isMandatory;
	
	@Embedded
	private CreateLog createLog;
	
	@Embedded
	private UpdateLog updateLog;
	
	@Column(name = "LIST_ORDER")
	private Long listOrder;
	
	@PostPersist
	private void setListOrder() {
	    this.listOrder = id;
	}

	
}

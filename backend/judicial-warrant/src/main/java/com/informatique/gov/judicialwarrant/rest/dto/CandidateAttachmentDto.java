package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "candidate", "attachmentType"})
@EqualsAndHashCode(of = {"candidate", "attachmentType"}, callSuper = false)
public class CandidateAttachmentDto implements UserModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1856946362391799061L;
	
	private Long id;
	private CandidateDto candidate;
	private AttachmentTypeDto attachmentType;
	private String ucmDocumentId;
}

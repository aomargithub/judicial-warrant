package com.informatique.gov.judicialwarrant.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "request", "attachmentType"})
@EqualsAndHashCode(of = {"request", "attachmentType"}, callSuper = false)
public class RequestAttachmentDto implements UserModel<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3565672297335548741L;

	private Long id;
	private RequestDto request;
	private AttachmentTypeDto attachmentType;
	private String ucmDocumentId;
	private String documentName;
	@JsonIgnore
	private Short version;
}

package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "request", "attachmentType"})
@EqualsAndHashCode(of = {"request", "attachmentType"}, callSuper = false)
public class RequestAttachmentDto implements UserModel<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3565672297335548741L;

	private Integer id;
	private RequestDto request;
	private AttachmentTypeDto attachmentType;
	private String ucmDocumentId;
}

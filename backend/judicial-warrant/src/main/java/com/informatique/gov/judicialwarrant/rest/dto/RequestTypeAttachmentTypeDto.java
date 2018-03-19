package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "requestType", "attachmentType"})
@EqualsAndHashCode(of = {"requestType", "attachmentType"}, callSuper = false)
public class RequestTypeAttachmentTypeDto implements UserModel<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6271858078928664072L;
	
	private Short id;
	private RequestTypeDto requestType;
	private AttachmentTypeDto attachmentType;
	private Boolean isActive;
	private Byte listOrder;
}

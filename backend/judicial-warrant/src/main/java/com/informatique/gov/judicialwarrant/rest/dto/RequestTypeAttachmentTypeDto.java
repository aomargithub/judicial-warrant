package com.informatique.gov.judicialwarrant.rest.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@NotNull
	private RequestTypeDto requestType;
	@NotNull
	private AttachmentTypeDto attachmentType;
	@NotNull
	private Boolean isActive;
	private Byte listOrder;
	@JsonIgnore
	private Short version;
}

package com.informatique.gov.judicialwarrant.rest.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "entitled", "attachmentType"})
@EqualsAndHashCode(of = {"entitled", "attachmentType"}, callSuper = false)
public class EntitledAttachmentDto implements UserModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1856946362391799061L;
	
	private Long id;
	private EntitledDto entitled;
	@NotNull
	private AttachmentTypeDto attachmentType;
	private String ucmDocumentId;
	private String documentName;
	@JsonIgnore
	private Short version;
	
}

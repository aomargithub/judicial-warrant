package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = { "id", "arabicName", "englishName" })
@EqualsAndHashCode(of = { "arabicName", "englishName" }, callSuper = false)
public class AttachmentTypeDto implements UserModel<Byte> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131509027915511341L;

	private Byte id;
	private String englishName;
	private String arabicName;
	private Boolean isActive;
	private Boolean isCandidateAttachment;
	private Byte listOrder;
}

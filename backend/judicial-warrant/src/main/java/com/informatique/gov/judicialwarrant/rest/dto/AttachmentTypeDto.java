package com.informatique.gov.judicialwarrant.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = { "id", "arabicName", "englishName" })
@EqualsAndHashCode(of = { "arabicName", "englishName" }, callSuper = false)
public class AttachmentTypeDto implements UserModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131509027915511341L;

	private Long id;
	@NotBlank
	private String englishName;
	@NotBlank
	private String arabicName;
	@NotNull
	private Boolean isActive;
	@NotNull
	private Boolean isCandidateAttachment;
	@NotNull
	private Boolean isMandatory;
	
	@Min(0)
	@NotNull
	private Byte listOrder;
	
	@JsonIgnore
	private Short version;
}

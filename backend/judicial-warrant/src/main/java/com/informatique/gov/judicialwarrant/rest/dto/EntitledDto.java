package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = { "id", "civilId", "arabicName", "englishName" })
@EqualsAndHashCode(of = { "civilId" }, callSuper = false)
public class EntitledDto implements UserModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7323040331827005680L;
	private Long id;
	private String englishName;
	private String arabicName;
	private Long civilId;
	@JsonIgnore
	private Short version;
	private String mobileNumber1;
	private String mobileNumber2;
	private String emailAddress;
	@NotNull
	private EntitledRegistrationDto entitledRegistration;
	private OrganizationUnitDto organizationUnit;
	private EntitledStatusDto currentStatus;
	private Set<EntitledAttachmentDto> attachments;
	private List<EntitledHistoryLogDto> histortyLogs;
}

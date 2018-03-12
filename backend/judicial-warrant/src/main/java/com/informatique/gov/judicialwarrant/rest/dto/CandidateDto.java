package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = { "id", "civilId", "request", "arabicName", "englishName" })
@EqualsAndHashCode(of = { "civilId", "request" }, callSuper = false)
public class CandidateDto implements UserModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7323040331827005680L;
	private Integer id;
	private String englishName;
	private String arabicName;
	private Long civilId;
	private String mobileNumber1;
	private String mobileNumber2;
	private String emailAddress;
	private OrganizationUnitDto organizationUnit;
	private CandidateStatusDto currentStatus;
	private RequestDto request;
	private List<CandidateHistoryLogDto> histortyLogs;
}

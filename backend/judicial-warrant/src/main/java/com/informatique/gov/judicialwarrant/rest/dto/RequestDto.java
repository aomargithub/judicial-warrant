package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "number"})
@EqualsAndHashCode(of = {"number"}, callSuper = false)
public class RequestDto implements UserModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	private Integer id;
	private String serial;
	private RequestTypeDto type;
	private RequestStatusDto currentStatus;
	private OrganizationUnitDto organizationUnit;
	private List<RequestHistoryLogDto> histortyLogs;
	
}

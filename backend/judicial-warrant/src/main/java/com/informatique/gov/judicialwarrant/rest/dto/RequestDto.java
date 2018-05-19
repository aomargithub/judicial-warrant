package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "serial"})
@EqualsAndHashCode(of = {"serial"}, callSuper = false)
public class RequestDto implements UserModel<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	private Long id;
	private String serial;
	@JsonIgnore
	private Short version;
	private RequestTypeDto type;
	private RequestStatusDto currentStatus;
	private RequestInternalStatusDto currentInternalStatus;
	private OrganizationUnitDto organizationUnit;
	private List<RequestHistoryLogDto> histortyLogs;
	
}

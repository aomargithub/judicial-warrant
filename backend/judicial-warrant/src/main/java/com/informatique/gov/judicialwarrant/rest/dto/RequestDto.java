package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private OrganizationUnitDto organizationUnit;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createDate;
	private List<RequestHistoryLogDto> histortyLogs;
	
}

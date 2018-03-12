package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "request", "status"})
@EqualsAndHashCode(of = {"request", "status"}, callSuper = false)
public class RequestHistoryLogDto implements UserModel<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 854525152394151545L;
	private Long id;
	private RequestDto request;
	private RequestStatusDto status;
	private String note;
	private UserDto createBy;
	private Date createDate;
}

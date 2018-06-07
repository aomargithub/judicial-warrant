package com.informatique.gov.judicialwarrant.rest.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "request"})
@EqualsAndHashCode(of = {"request"}, callSuper = false)
public class CapacityDelegationDto implements UserModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private RequestDto request;
	@NotNull
	private String jobTitle;
	@NotNull
	private Long lawNo;
}

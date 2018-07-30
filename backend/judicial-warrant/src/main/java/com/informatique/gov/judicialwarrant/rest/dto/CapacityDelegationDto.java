package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String ministerialDecreeNumber;
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date ministerialDecreeDate;
	@JsonIgnore
	private Short version;
}

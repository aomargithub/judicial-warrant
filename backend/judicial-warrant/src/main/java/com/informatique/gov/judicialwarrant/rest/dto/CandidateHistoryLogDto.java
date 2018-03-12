package com.informatique.gov.judicialwarrant.rest.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "candidate", "status"})
@EqualsAndHashCode(of = {"candidate", "status"}, callSuper = false)
public class CandidateHistoryLogDto implements UserModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9149014565684009065L;

	private Integer id;
	private CandidateDto candidate;
	private CandidateStatusDto status;
	private String note;
	private UserDto createBy;
	private Date createDate;
}

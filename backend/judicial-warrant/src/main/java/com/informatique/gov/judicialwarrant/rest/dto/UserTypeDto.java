package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "code"})
@EqualsAndHashCode(of = {"id", "code"}, callSuper = false)
public class UserTypeDto implements UserModel<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2903233198653464352L;
	private Integer id;
	private String code;
}

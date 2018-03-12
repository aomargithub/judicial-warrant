package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "loginName", "civilId"})
@EqualsAndHashCode(of = {"loginName", "civilId"}, callSuper = false)
public class UserDto implements UserModel<Integer> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8415889490581393136L;
	
	private Integer id;
	private String englishName;
	private String arabicName;
	private Boolean isActive;
	private String mobileNumber1;
	private String mobileNumber2;
	private String emailAddress;
	private OrganizationUnitDto organizationUnit;
	private RoleDto role;
	private Long civilId;
	private String loginName;
}

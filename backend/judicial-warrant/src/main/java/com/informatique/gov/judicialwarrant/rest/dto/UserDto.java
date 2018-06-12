package com.informatique.gov.judicialwarrant.rest.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Email
	@NotBlank
	private String emailAddress;
	private OrganizationUnitDto organizationUnit;
	@NotNull
	private RoleDto role;
	@NotNull
	private Long civilId;
	@NotNull
	private String loginName;
	@JsonIgnore
	private Short version;
	private UserTypeDto userType;
	
}

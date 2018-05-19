package com.informatique.gov.judicialwarrant.rest.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDetailsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2744942566995118203L;
	private String username;
	private String role;
	private String englishName;
	private String arabicName;
	private String mobileNumber1;
	private String mobileNumber2;
	private String emailAddress;
	private Long civilId;
	private OrganizationUnitDto organizationUnit;
	private UserTypeDto userType;
	private AuthenticationTokenDto token;
}

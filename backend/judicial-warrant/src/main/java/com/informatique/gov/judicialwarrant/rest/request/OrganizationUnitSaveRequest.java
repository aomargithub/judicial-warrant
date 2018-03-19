package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;

import lombok.Data;

@Data
public class OrganizationUnitSaveRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1955314092395996461L;
	
	private OrganizationUnitDto organizationUnit;

}

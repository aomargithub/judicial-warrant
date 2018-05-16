package com.informatique.gov.judicialwarrant.rest.response;


import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestInternalStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "serial"})
@EqualsAndHashCode(of = {"serial"}, callSuper = false)
public class JwcdRequestForInternalResponse implements UserModel<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	private Long id;
	private String serial;
	private RequestTypeDto type;
	private RequestInternalStatusDto currentInternalStatus;
	private OrganizationUnitDto organizationUnit;
	private String jobTitleName;
	
}

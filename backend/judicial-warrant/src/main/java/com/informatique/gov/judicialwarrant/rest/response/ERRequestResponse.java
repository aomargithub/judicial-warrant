package com.informatique.gov.judicialwarrant.rest.response;


import java.util.Set;

import com.informatique.gov.judicialwarrant.rest.dto.CandidateDto;
import com.informatique.gov.judicialwarrant.rest.dto.OrganizationUnitDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestStatusDto;
import com.informatique.gov.judicialwarrant.rest.dto.RequestTypeDto;
import com.informatique.gov.judicialwarrant.rest.dto.UserModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "serial"})
@EqualsAndHashCode(of = {"serial"}, callSuper = false)
public class ERRequestResponse implements UserModel<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	private Long id;
	private String serial;
	private RequestTypeDto type;
	private OrganizationUnitDto organizationUnit;
	private RequestStatusDto currentStatus;
	private JwcdRequestResponse jwcdRequestDto;
	private Set<CandidateDto> candidates;
	
	
}

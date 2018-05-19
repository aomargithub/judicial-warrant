package com.informatique.gov.judicialwarrant.rest.request;


import java.io.Serializable;
import java.util.Set;

import com.informatique.gov.judicialwarrant.rest.dto.CandidateDto;
import com.informatique.gov.judicialwarrant.rest.response.JwcdRequestResponse;

import lombok.Data;

@Data
public class ERRequestRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046915499323327588L;
	private Long id;
	private String serial;
	private JwcdRequestResponse jwcdRequestDto;
	private Set<CandidateDto> candidates;
	
	
}

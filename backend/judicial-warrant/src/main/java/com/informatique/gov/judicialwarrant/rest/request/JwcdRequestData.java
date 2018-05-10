package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
public class JwcdRequestData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1955314092395996461L;
	
	@NotEmpty
	@NotBlank
	@NotNull
	private String jobTitle;
	
	@NotEmpty
	@NotBlank
	@NotNull
	private String notes;

}

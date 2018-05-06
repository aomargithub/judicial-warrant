package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class JwcdRequestNotesData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1955314092395996461L;
	
	@NotEmpty
	@NotBlank
	@NotNull
	private String notes;

}

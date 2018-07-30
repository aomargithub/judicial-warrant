package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PasswordChangeRequest implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -5651364537877746246L;

	@NotNull
	private String oldPassword;
	@NotNull
	private String newPassword;
}

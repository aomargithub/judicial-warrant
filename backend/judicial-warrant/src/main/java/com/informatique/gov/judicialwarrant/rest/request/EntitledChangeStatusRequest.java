package com.informatique.gov.judicialwarrant.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class EntitledChangeStatusRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698575712022936099L;

	@NotNull
	private String note;
}

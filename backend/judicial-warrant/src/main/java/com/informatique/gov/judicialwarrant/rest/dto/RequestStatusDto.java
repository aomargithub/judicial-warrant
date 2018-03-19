package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class RequestStatusDto implements UserModel<Byte> {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8973371922744404362L;

	private Byte id;
	private String code;
	private String englishName;
	private String arabicName;
}

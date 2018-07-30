package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class NationalityDto implements UserModel<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8695777278368168503L;
	
	private Short id;
	private String englishName;
	private String arabicName;
	private String code;
	private Long iso;
}

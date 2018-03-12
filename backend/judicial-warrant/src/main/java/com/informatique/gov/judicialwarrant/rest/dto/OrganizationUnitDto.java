package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(of = {"id", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"arabicName", "englishName"}, callSuper = false)
public class OrganizationUnitDto implements UserModel<Short> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4750673549591061137L;
	
	
    private Short id;
	private String englishName;
	private String arabicName;
	private Boolean isActive;
	private Short listOrder;
}

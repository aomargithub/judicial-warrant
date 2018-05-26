package com.informatique.gov.judicialwarrant.rest.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code", "arabicName", "englishName"}, callSuper = false)
public class RoleDto implements UserModel<Byte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5650702934453689747L;
	private Byte id;
	private String code;
	private String englishName;
	private String arabicName;
	private Boolean isActive;
	private Byte listOrder;
	private String ldapSecurityGroup;
}

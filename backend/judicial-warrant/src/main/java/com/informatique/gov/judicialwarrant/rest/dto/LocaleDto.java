package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"code", "name"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class LocaleDto implements UserModel<Byte>{
	
    private static final long serialVersionUID = 2621227644778056739L;
    private Byte id;
    private String name;
    private Boolean isActive;
    private String code;
}

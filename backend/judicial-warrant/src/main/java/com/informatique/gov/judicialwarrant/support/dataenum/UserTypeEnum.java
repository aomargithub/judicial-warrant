package com.informatique.gov.judicialwarrant.support.dataenum;

import lombok.Getter;

public enum UserTypeEnum {
EXTERNAL("external"),
INTERNAL("internal");
	
	@Getter
    private String code;
	

	
	private UserTypeEnum(String code) {
		this.code = code;
	}
	
}

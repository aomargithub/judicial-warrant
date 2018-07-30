package com.informatique.gov.judicialwarrant.support.dataenum;

import lombok.Getter;

public enum UserTypeEnum {
EXTERNAL("EXTERNAL"),
INTERNAL("INTERNAL");
	
	@Getter
    private String code;
	

	
	private UserTypeEnum(String code) {
		this.code = code;
	}
	
}

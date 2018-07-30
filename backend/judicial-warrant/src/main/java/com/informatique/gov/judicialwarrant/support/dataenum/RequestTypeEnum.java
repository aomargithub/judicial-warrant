package com.informatique.gov.judicialwarrant.support.dataenum;


import lombok.Getter;

public enum RequestTypeEnum {
	
	CAPACITY_DELEGATION("CAPACITY_DELEGATION", ""),
	ENTITLED_REGISTRATION("ENTITLED_REGISTRATION", "");
	
	@Getter
    private String code;
	
	@Getter
    private String description;

	
	private RequestTypeEnum(String code, String description) {
		this.code = code;
	}
	
	
}

package com.informatique.gov.judicialwarrant.support.dataenum;


import lombok.Getter;

public enum RequestTypeEnum {
	
	CAPACITY_DELEGATION("JT", ""),
	Entitled_REGISTRATION("ER", "");
	
	@Getter
    private String code;
	
	@Getter
    private String description;

	
	private RequestTypeEnum(String code, String description) {
		this.code = code;
	}
	
	
}

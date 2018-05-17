package com.informatique.gov.judicialwarrant.support.dataenum;


import lombok.Getter;

public enum RequestTypeEnum {
	
	JWCD("JT", ""),
	ER("ER", "");
	
	@Getter
    private String code;
	
	@Getter
    private String description;

	
	private RequestTypeEnum(String code, String description) {
		this.code = code;
	}
	
	
}

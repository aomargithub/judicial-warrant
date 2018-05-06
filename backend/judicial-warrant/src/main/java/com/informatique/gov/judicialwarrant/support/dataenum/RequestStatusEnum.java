package com.informatique.gov.judicialwarrant.support.dataenum;

import lombok.Getter;

public enum RequestStatusEnum {
	
	DRAFT("DRAFT"),
	SUBMITED("SUBMITED"),
	INCOMPLETE("INCOMPLETE"),
	INPROGRESS("INPROGRESS"),
	REJECTED("REJECTED"),
	ISSUED("ISSUED");
	
	
	
	@Getter
    private String code;

	
	private RequestStatusEnum(String code) {
		this.code = code;
	}
	
	
	public static RequestStatusEnum getByCode(String code) {
		for(RequestStatusEnum statusEnum : RequestStatusEnum.values()) {
			if(statusEnum.code.equals(code)) {
				return statusEnum;
			}
		}
		return null;
	}
	
	
}

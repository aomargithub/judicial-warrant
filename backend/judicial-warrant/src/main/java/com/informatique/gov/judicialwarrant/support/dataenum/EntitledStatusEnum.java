package com.informatique.gov.judicialwarrant.support.dataenum;

import lombok.Getter;

public enum EntitledStatusEnum {
	
	DRAFT("DRAFT"),
	SUBMITED("SUBMITED"),
	INCOMPLETE("INCOMPLETE"),
	INPROGRESS("INPROGRESS"),
	ACCEPTED("ACCEPTED"),
	REJECTED("REJECTED"),
	TRAINNING("TRAINNING"),
	PASSED("PASSED"),
	FAILED("FAILED"),
	CARD_RECIEVED("CARD_RECIEVED");
	
	
	
	@Getter
    private String code;

	
	private EntitledStatusEnum(String code) {
		this.code = code;
	}
	
	
	public static EntitledStatusEnum getByCode(String code) {
		for(EntitledStatusEnum statusEnum : EntitledStatusEnum.values()) {
			if(statusEnum.code.equals(code)) {
				return statusEnum;
			}
		}
		return null;
	}
	
	
}

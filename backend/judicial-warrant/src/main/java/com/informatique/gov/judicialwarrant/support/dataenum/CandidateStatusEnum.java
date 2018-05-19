package com.informatique.gov.judicialwarrant.support.dataenum;

import lombok.Getter;

public enum CandidateStatusEnum {
	
	SUBMITED("SUBMITED"),
	INCOMPLETE("INCOMPLETE"),
	ACCEPTED("ACCEPTED"),
	REJECTED("REJECTED"),
	TRAINNING("TRAINNING"),
	PASSED("PASSED"),
	FAILED("FAILED"),
	CARD_RECIEVED("CARD_RECIEVED");
	
	
	
	@Getter
    private String code;

	
	private CandidateStatusEnum(String code) {
		this.code = code;
	}
	
	
	public static CandidateStatusEnum getByCode(String code) {
		for(CandidateStatusEnum statusEnum : CandidateStatusEnum.values()) {
			if(statusEnum.code.equals(code)) {
				return statusEnum;
			}
		}
		return null;
	}
	
	
}
